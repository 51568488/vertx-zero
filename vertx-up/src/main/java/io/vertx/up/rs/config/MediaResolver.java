package io.vertx.up.rs.config;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Media resolver
 * 1. consumes ( default = application/json )
 * 2. produces ( default = application/json )
 */
class MediaResolver {

    private static final Annal LOGGER = Annal.get(MediaResolver.class);

    /**
     * Capture the consume media types
     *
     * @param method
     * @return
     */
    public static Set<MediaType> consumes(final Method method) {
        return resolve(method, Consumes.class);
    }

    /**
     * Capture the produces media types
     *
     * @param method
     * @return
     */
    public static Set<MediaType> produces(final Method method) {
        return resolve(method, Produces.class);
    }

    private static Set<MediaType> resolve(final Method method,
                                          final Class<? extends Annotation>
                                                  mediaCls) {
        return Fn.get(() -> {
            final Annotation anno = method.getAnnotation(mediaCls);
            return Fn.getSemi(null == anno, LOGGER,
                    ConcurrentHashSet::new,
                    () -> {
                        final String[] value = Instance.invoke(anno, "value");
                        final Set<MediaType> result = new ConcurrentHashSet<>();
                        for (final String item : value) {
                            if (null != item) {
                                // Resolve media type: Use jersey library to parse.
                                final MediaType type = MediaType.valueOf(item);
                                if (null != type) {
                                    result.add(type);
                                }
                            }
                        }
                        /** Ward Card for default **/
                        if (result.isEmpty()) {
                            result.add(MediaType.WILDCARD_TYPE);
                        }
                        return result;
                    });
        }, method, mediaCls);
    }
}
