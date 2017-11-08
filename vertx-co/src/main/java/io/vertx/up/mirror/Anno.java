package io.vertx.up.mirror;

import com.vie.hoc.HNull;
import com.vie.hoc.HPool;

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

    private Anno() {
    }
}
