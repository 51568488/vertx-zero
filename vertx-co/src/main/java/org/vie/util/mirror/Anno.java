package org.vie.util.mirror;

import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.fun.HPool;
import org.vie.util.Instance;

import java.lang.annotation.Annotation;
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

    public static Annotation get(final Class<?> clazz,
                                 final Class<? extends Annotation> annoCls) {
        return HNull.get(() -> clazz.getDeclaredAnnotation(annoCls), clazz, annoCls);
    }

    public static Annotation get(final Method method,
                                 final Class<? extends Annotation> annoCls) {
        return HNull.get(() -> method.getDeclaredAnnotation(annoCls), method, annoCls);
    }

    /**
     * Get all annotation
     *
     * @param method
     * @return
     */
    public static Annotation[] get(final Method method) {
        return HNull.get(() -> method.getDeclaredAnnotations(), method);
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
     * Check whether clazz contains annotationCls marked.
     *
     * @param clazz
     * @param annotationCls
     * @return
     */
    public static boolean isMark(final Class<?> clazz,
                                 final Class<? extends Annotation> annotationCls) {
        return HBool.exec(null == clazz || null == annotationCls,
                () -> false,
                () -> clazz.isAnnotationPresent(annotationCls));
    }

    /**
     * Check whether clazz contains annotationCls marked.
     *
     * @param method
     * @param annotationCls
     * @return
     */
    public static boolean isMark(final Method method,
                                 final Class<? extends Annotation> annotationCls) {
        return HBool.exec(null == method || null == annotationCls,
                () -> false,
                () -> method.isAnnotationPresent(annotationCls));
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
