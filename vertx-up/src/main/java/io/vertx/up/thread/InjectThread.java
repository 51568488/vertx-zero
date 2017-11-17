package io.vertx.up.thread;

import io.vertx.up.eon.Plugins;
import io.vertx.up.exception.MultiAnnotatedException;
import io.vertx.zero.eon.Values;
import io.vertx.zero.func.HBool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InjectThread extends Thread {

    private static final Annal LOGGER = Annal.get(InjectThread.class);

    private final ConcurrentMap<String, Class<?>> fieldMap = new ConcurrentHashMap<>();

    private final transient Class<?> reference;
    private final transient Set<Class<?>> namedSet;
    private final transient Set<Class<?>> classes;

    public InjectThread(final Class<?> clazz, final Set<Class<?>> classes) {
        this.setName("zero-injection-scanner-" + this.getId());
        this.reference = clazz;
        this.classes = classes;
        this.namedSet = classes.stream()
                .filter((item) -> item.isAnnotationPresent(Named.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void run() {
        if (null != this.reference) {
            // 1. Read all inject point
            final List<Field> fields = Arrays.stream(this.reference.getDeclaredFields())
                    .filter(field -> Anno.isMark(field, Plugins.INJECT_ANNOTATIONS))
                    .collect(Collectors.toList());
            // 2. Convert to fields
            for (final Field field : fields) {
                // 3. Specific
                if (field.isAnnotationPresent(Inject.class)) {
                    this.scanStandard(field);
                } else {
                    this.scanSpecific(field);
                }
            }
        }
    }

    private void scanStandard(final Field field) {
        // JSR 330
        final Class<?> type = field.getType();
        if (type.isInterface()) {
            // Interface
            final Set<Class<?>> target = this.classes.stream().filter(
                    item -> Instance.isMatch(item, type)
            ).collect(Collectors.toSet());
            System.out.println(type.getName());
            System.out.println(target);
        } else {
            this.fieldMap.put(field.getName(), type);
            LOGGER.info(Info.SCANED_FIELD, type.getName(),
                    field.getName(), Inject.class);
        }

    }

    private void scanSpecific(final Field field) {
        // Vert.x Defined
        final Set<Class<? extends Annotation>> DEFINED
                = Plugins.INFIX_MAP.keySet();
        final Annotation[] annotations = field.getDeclaredAnnotations();
        // Annotation counter
        final Set<String> set = new HashSet<>();
        Annotation hitted = null;
        for (final Annotation annotation : annotations) {
            if (DEFINED.contains(annotation.annotationType())) {
                hitted = annotation;
                set.add(annotation.annotationType().getName());
            }
        }
        // Duplicated annotated
        HBool.execUp(Values.ONE < set.size(), LOGGER,
                MultiAnnotatedException.class, getClass(),
                field.getName(), field.getDeclaringClass().getName(), set);
        // Fill typed directly.
        LOGGER.info(Info.SCANED_FIELD, field.getDeclaringClass().getName(),
                field.getName(), hitted.annotationType().getName());
        this.fieldMap.put(field.getName(), field.getType());
    }

    public ConcurrentMap<String, Class<?>> getFieldMap() {
        return this.fieldMap;
    }

    public Class<?> getClassKey() {
        return this.reference;
    }

    public boolean isEmpty() {
        return this.fieldMap.isEmpty();
    }
}
