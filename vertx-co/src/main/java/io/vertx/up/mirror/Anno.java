package io.vertx.up.mirror;

import com.vie.fun.HBool;
import com.vie.fun.HNull;
import com.vie.fun.HPool;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentMap;

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
                () -> null != clazz.getDeclaredAnnotation(annotationCls));
    }

    private Anno() {
    }
}
