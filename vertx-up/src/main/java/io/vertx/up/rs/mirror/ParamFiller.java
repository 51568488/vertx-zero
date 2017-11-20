package io.vertx.up.rs.mirror;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.exception.ParameterConflictException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Filler;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.tool.mirror.Instance;

import javax.ws.rs.DefaultValue;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Arguments process, ParamFilter.
 */
public class ParamFiller {

    private static final Annal LOGGER = Annal.get(ParamFiller.class);

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
                if (Filler.PARAMS.containsKey(key)) {
                    String name = null;
                    // Filter the annotation which is not defined "value"
                    if (!Filler.NO_VALUE.contains(key)) {
                        name = Instance.invoke(anno, "value");
                    }
                    final Filler filler = Filler.PARAMS.get(key);
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
            if (Filler.PARAMS.containsKey(anno.annotationType())) {
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
        return Fn.getSemi(
                null != anno && anno.isPresent(), LOGGER,
                anno::get,
                () -> null);
    }
}
