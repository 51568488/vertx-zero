package io.vertx.up.web.thread;

import io.vertx.up.annotations.Qualifier;
import io.vertx.up.eon.Plugins;
import io.vertx.up.exception.MultiAnnotatedException;
import io.vertx.up.exception.NamedImplementionException;
import io.vertx.up.exception.NamedNotFoundException;
import io.vertx.up.exception.QualifierMissedException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.vertx.zero.tool.StringUtil;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class AffluxThread extends Thread {

    private static final Annal LOGGER = Annal.get(AffluxThread.class);

    private final ConcurrentMap<String, Class<?>> fieldMap = new ConcurrentHashMap<>();

    private final transient Class<?> reference;
    private final transient Set<Class<?>> namedSet;
    private final transient Set<Class<?>> classes;

    public AffluxThread(final Class<?> clazz, final Set<Class<?>> classes) {
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
            final List<Class<?>> target = this.classes.stream().filter(
                    item -> Instance.isMatch(item, type)
            ).collect(Collectors.toList());
            // Unique
            if (Values.ONE == target.size()) {
                final Class<?> targetCls = target.get(Values.IDX);
                LOGGER.info(Info.SCANED_FIELD, this.reference,
                        field.getName(), targetCls.getName(), Inject.class);
                this.fieldMap.put(field.getName(), targetCls);
            } else {
                // By Named and Qualifier
                scanQualifier(field, target);
            }
        } else {
            this.fieldMap.put(field.getName(), type);
            LOGGER.info(Info.SCANED_FIELD, this.reference,
                    field.getName(), type.getName(), Inject.class);
        }

    }

    private void scanQualifier(final Field field,
                               final List<Class<?>> instanceCls) {
        // Field must annotated with @Qualifier
        final Annotation annotation = field.getAnnotation(Qualifier.class);

        Fn.flingUp(null == annotation,
                LOGGER, QualifierMissedException.class,
                getClass(), field.getName(), field.getDeclaringClass().getName());

        // All implementation class must be annotated with @Named
        final boolean match = instanceCls.stream()
                .allMatch(item -> item.isAnnotationPresent(Named.class));

        final Set<String> names = instanceCls.stream()
                .map(Class::getName).collect(Collectors.toSet());

        Fn.flingUp(!match,
                LOGGER, NamedImplementionException.class,
                getClass(), names, field.getType().getName());

        // Named value must be reflect with @Qualifier
        final String value = Instance.invoke(annotation, "value");

        final Optional<Class<?>> verified = instanceCls.stream()
                .filter(item -> {
                    final Annotation target = item.getAnnotation(Named.class);
                    final String targetValue = Instance.invoke(target, "value");
                    return value.equals(targetValue)
                            && !StringUtil.isNil(targetValue);
                }).findAny();

        Fn.flingUp(!verified.isPresent(),
                LOGGER, NamedNotFoundException.class,
                getClass(), names, value);

        // Passed all specification
        this.fieldMap.put(field.getName(), verified.get());
    }

    private void scanSpecific(final Field field) {
        // Vert.x Defined
        final Set<Class<? extends Annotation>> defineds
                = Plugins.INFIX_MAP.keySet();
        final Annotation[] annotations = field.getDeclaredAnnotations();
        // Annotation counter
        final Set<String> set = new HashSet<>();
        Annotation hitted = null;
        for (final Annotation annotation : annotations) {
            if (defineds.contains(annotation.annotationType())) {
                hitted = annotation;
                set.add(annotation.annotationType().getName());
            }
        }
        // Duplicated annotated
        Fn.flingUp(Values.ONE < set.size(), LOGGER,
                MultiAnnotatedException.class, getClass(),
                field.getName(), field.getDeclaringClass().getName(), set);
        // Fill typed directly.
        LOGGER.info(Info.SCANED_FIELD, this.reference,
                field.getName(),
                field.getDeclaringClass().getName(),
                hitted.annotationType().getName());
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
