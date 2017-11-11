package io.vertx.up.rs.config;

import io.vertx.core.http.HttpMethod;
import org.vie.exception.up.MethodNullException;
import org.vie.fun.HBool;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Method Resolver
 */
class MethodResolver {

    private static final Annal LOGGER = Annal.get(MethodResolver.class);

    private static final ConcurrentMap<Class<?>, HttpMethod> METHODS =
            new ConcurrentHashMap<Class<?>, HttpMethod>() {
                {
                    put(GET.class, HttpMethod.GET);
                    put(POST.class, HttpMethod.POST);
                    put(PUT.class, HttpMethod.PUT);
                    put(DELETE.class, HttpMethod.DELETE);
                    put(OPTIONS.class, HttpMethod.OPTIONS);
                    put(HEAD.class, HttpMethod.HEAD);
                    put(PATCH.class, HttpMethod.PATCH);
                }
            };

    public static HttpMethod resolve(final Method method) {
        // 1. Method checking.
        HBool.execUp(null == method, LOGGER,
                MethodNullException.class, MethodResolver.class);
        final Annotation[] annotations = Anno.get(method);
        // 2. Method ignore
        HttpMethod result = null;
        for (final Annotation annotation : annotations) {
            final Class<?> key = annotation.annotationType();
            if (METHODS.containsKey(key)) {
                result = METHODS.get(key);
                break;
            }
        }
        // 2. Ignore this method.
        if (null == result) {
            LOGGER.info(Info.METHOD_IGNORE, method.getName());
        }
        return result;
    }
}
