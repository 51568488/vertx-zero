package io.vertx.up.rs.mirror;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.exception.ParameterConflictException;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Filler;
import io.vertx.up.rs.argument.*;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.func.HBool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import javax.ws.rs.*;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Arguments process, ParamFilter.
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
            Fn.flingUp(!byAnnotated(paramAnnos), LOGGER,
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
            returnValue = TypedFilter.get(paramType, context);
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

    private static Object getDefault(final Annotation[] annotations,
                                     final Class<?> paramType) {
        final Annotation defaultValue = getDefault(annotations);
        Object dft = null;
        if (null != defaultValue) {
            // 1.1. Return default value direactly
            dft = ZeroSerializer.getValue(paramType,
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
}
