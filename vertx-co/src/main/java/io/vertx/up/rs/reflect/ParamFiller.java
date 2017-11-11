package io.vertx.up.rs.reflect;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.up.rs.Filler;
import io.vertx.up.rs.argument.*;
import org.vie.exception.up.ParameterConflictException;
import org.vie.fun.HBool;
import org.vie.util.Instance;
import org.vie.util.Types;
import org.vie.util.log.Annal;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Arguments process bus
 */
public class ParamFiller {

    private static final Annal LOGGER = Annal.get(ParamFiller.class);

    private static final ConcurrentMap<Class<? extends Annotation>, Filler> PARAMS =
            new ConcurrentHashMap<Class<? extends Annotation>, Filler>() {
                {
                    // JSR311 Provided
                    put(QueryParam.class, Instance.singleton(QueryFiller.class));
                    put(FormParam.class, Instance.singleton(FormFiller.class));
                    put(MatrixParam.class, Instance.singleton(MatrixFiller.class));
                    put(PathParam.class, Instance.singleton(PathFiller.class));
                    put(HeaderParam.class, Instance.singleton(HeaderFiller.class));
                    put(CookieParam.class, Instance.singleton(CookieFiller.class));
                    // Extension
                    put(BodyParam.class, Instance.singleton(BodyFiller.class));
                    put(StreamParam.class, Instance.singleton(StreamFiller.class));
                    put(SessionParam.class, Instance.singleton(SessionFiller.class));
                }
            };

    private static final Set<Class<? extends Annotation>> NO_VALUE =
            new ConcurrentHashSet<Class<? extends Annotation>>() {
                {
                    add(BodyParam.class);
                    add(StreamParam.class);
                }
            };

    /**
     * Process to build parameters.
     *
     * @param context
     * @param paramType
     * @param paramAnnos
     * @return
     */
    public static Object process(
            final RoutingContext context,
            final Class<?> paramType,
            final Annotation[] paramAnnos) {
        Object returnValue = null;
        if (0 < paramAnnos.length) {
            // 1. Get default value from annotation directly
            final Object dft = getDefault(paramAnnos, paramType);

            // 2. Exception checking.
            HBool.execUp(!byAnnotated(paramAnnos), LOGGER,
                    ParameterConflictException.class,
                    ParamFiller.class, paramType);

            // 3. Current param is annotated
            for (final Annotation anno : paramAnnos) {
                final Class<?> key = anno.annotationType();
                if (PARAMS.containsKey(key)) {
                    String name = null;
                    // Filter the annotation which is not defined "value"
                    if (!NO_VALUE.contains(key)) {
                        name = Instance.invoke(anno, "value");
                    }
                    final Filler filler = PARAMS.get(key);
                    if (null != filler) {
                        returnValue = filler.apply(name, paramType, context);
                    }
                }
            }
            if (null == returnValue) {
                returnValue = dft;
            }
        } else {
            // Type reference scanned
            returnValue = reference(paramType, context);
        }
        return returnValue;
    }

    private static boolean byAnnotated(final Annotation[] annotations) {
        boolean by = false;
        for (final Annotation anno : annotations) {
            if (PARAMS.containsKey(anno.annotationType())) {
                by = true;
                break;
            }
        }
        return by;
    }

    private static Object getDefault(final Annotation[] annotations, final Class<?> paramType) {
        final Annotation defaultValue = getDefault(annotations);
        Object dft = null;
        if (null != defaultValue) {
            // 1.1. Return default value direactly
            dft = Types.fromString(paramType,
                    Instance.invoke(defaultValue, "value"));
        }
        return dft;
    }

    private static Annotation getDefault(final Annotation[] annotations) {
        return get(annotations, DefaultValue.class);
    }

    private static Annotation get(final Annotation[] annotations,
                                  final Class<? extends Annotation> clazz) {
        final List<Annotation> list = Arrays.asList(annotations);
        final Optional<Annotation> anno = list.stream()
                .filter(item -> null != item &&
                        item.annotationType() == clazz)
                .findFirst();
        return HBool.exec(
                null != anno && anno.isPresent(),
                anno::get,
                () -> null);
    }

    private static Object reference(final Class<?> paramType,
                                    final RoutingContext context) {
        Object returnValue = null;
        if (Session.class == paramType) {
            // Session Object
            returnValue = context.session();
        } else if (HttpServerRequest.class == paramType) {
            // Request Object
            returnValue = context.request();
        } else if (HttpServerResponse.class == paramType) {
            // Response Object
            returnValue = context.response();
        } else if (Vertx.class == paramType) {
            // Vertx Object
            returnValue = context.vertx();
        } else if (EventBus.class == paramType) {
            // Eventbus Object
            returnValue = context.vertx().eventBus();
        } else if (User.class == paramType) {
            // User Objbect
            returnValue = context.user();
        }
        return returnValue;
    }
}
