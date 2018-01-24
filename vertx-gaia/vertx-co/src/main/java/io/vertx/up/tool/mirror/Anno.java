package io.vertx.up.tool.mirror;

import io.vertx.up.func.Fn;
import io.vertx.up.tool.container.KeyPair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class Anno {

    public static ConcurrentMap<String, Annotation> get(final Class<?> clazz) {
        return Fn.get(() -> {
            final Annotation[] annotationes = clazz.getDeclaredAnnotations();
            // Zapper
            return Fn.zipper(annotationes,
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
        return Fn.get(() -> {
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
     * Check whether current field marked with specific annotation
     *
     * @param field
     * @param annoCls
     * @return
     */
    public static boolean isMark(final Field field,
                                 final Set<Class<? extends Annotation>> annoCls) {
        return annoCls.stream().anyMatch(field::isAnnotationPresent);
    }

    /**
     * Check whether the annotations contain annotation that refer to annoCls.
     *
     * @param annotations
     * @param annoCls
     * @return
     */
    public static int occurs(final Annotation[][] annotations,
                             final Class<? extends Annotation> annoCls) {
        final AtomicInteger integer = new AtomicInteger(0);
        Fn.itMatrix(annotations, (annotation) -> {
            if (annotation.annotationType() == annoCls) {
                integer.incrementAndGet();
            }
        });
        return integer.get();
    }

    /**
     * Find type of parameter annotatated with annoCls.
     *
     * @param method
     * @param annoCls
     * @return
     */
    public static KeyPair<Integer, Class<?>> findParameter(
            final Method method,
            final Class<? extends Annotation> annoCls) {
        int index = 0;
        final KeyPair<Integer, Class<?>> result = KeyPair.create();
        for (final Parameter parameter : method.getParameters()) {
            if (parameter.isAnnotationPresent(annoCls)) {
                result.set(index, parameter.getType());
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Check wether class contains annotated field
     *
     * @param clazz
     * @param annoCls
     * @return
     */
    public static boolean isMark(final Class<?> clazz,
                                 final Set<Class<? extends Annotation>> annoCls) {
        final List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        return fields.stream().anyMatch(field -> isMark(field, annoCls));
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
