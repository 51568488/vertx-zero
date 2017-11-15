package io.vertx.zero.tool.mirror;

import io.vertx.up.eon.Plugins;
import io.vertx.zero.func.HNull;
import io.vertx.zero.func.HPool;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public final class Anno {

    public static ConcurrentMap<String, Annotation> get(final Class<?> clazz) {
        return HNull.get(() -> {
            final Annotation[] annotationes = clazz.getDeclaredAnnotations();
            // Zapper
            return HPool.zapper(annotationes,
                    (item) -> item.annotationType().getName(),
                    (item) -> item);
        }, clazz);
    }

    /**
     * Query clazz's methods to getPlugin all annotated spec annotations.
     *
     * @param clazz
     * @param methodCls
     * @return
     */
    public static Annotation[] query(final Class<?> clazz,
                                     final Class<? extends Annotation> methodCls) {
        return HNull.get(() -> {
            final Method[] methods = clazz.getDeclaredMethods();
            final List<Method> methodSet = Arrays.asList(methods);
            final List<Method> result = methodSet.stream()
                    .filter(item -> item.isAnnotationPresent(methodCls))
                    .collect(Collectors.toList());
            final List<Annotation> resultAnnos = new ArrayList<>();
            for (final Method method : result) {
                final Annotation anno = method.getAnnotation(methodCls);
                if (null != anno) {
                    resultAnnos.add(anno);
                }
            }
            return resultAnnos.toArray(new Annotation[]{});
        }, clazz, methodCls);
    }

    /**
     * Get current field plugin ( Each field should set only one standard plugin )
     *
     * @param field
     * @return
     */
    public static String getPlugin(final Field field) {
        return HNull.get(() -> {
            final Annotation[] annotations = field.getDeclaredAnnotations();
            String key = null;
            for (final Class<? extends Annotation> annoCls : Plugins.INFIX_MAP.keySet()) {
                if (field.isAnnotationPresent(annoCls)) {
                    key = Plugins.INFIX_MAP.get(annoCls);
                    break;
                }
            }
            return key;
        }, field);
    }


    public static <T, E extends Annotation> T getAttribute(final Class<?> clazz,
                                                           final Class<E> annoCls,
                                                           final String attr) {
        final E annotation = clazz.getAnnotation(annoCls);
        return Instance.invoke(annotation, attr);
    }

    private Anno() {
    }
}
