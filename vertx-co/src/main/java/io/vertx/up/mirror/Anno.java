package io.vertx.up.mirror;

import com.vie.hoc.HNull;
import com.vie.hoc.HPool;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentMap;

public class Anno {

    public static ConcurrentMap<String, Annotation> get(final Class<?> clazz) {
        return HNull.get(() -> {
            final Annotation[] annotationes = clazz.getAnnotations();
            // Zapper
            return HPool.zapper(annotationes,
                    (item) -> item.getClass().getName(),
                    (item) -> item);
        }, clazz);
    }
}
