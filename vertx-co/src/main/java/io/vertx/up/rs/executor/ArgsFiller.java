package io.vertx.up.rs.executor;

import io.vertx.ext.web.RoutingContext;
import org.vie.fun.HBool;
import org.vie.util.Instance;
import org.vie.util.Types;
import org.vie.util.log.Annal;

import javax.ws.rs.DefaultValue;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Arguments process bus
 */
class ArgsFiller {

    private static final Annal LOGGER = Annal.get(ArgsFiller.class);

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
        // 1. Check if default value
        final Annotation defaultValue = getDefault(paramAnnos);
        if (null != defaultValue) {
            // 1.1. Return default value direactly
            return Types.fromString(paramType,
                    Instance.invoke(defaultValue, "value"));
        }
        return "";
    }

    private static Annotation getDefault(final Annotation[] annotations) {
        final List<Annotation> list = Arrays.asList(annotations);
        final Optional<Annotation> anno = list.stream()
                .filter(item -> null != item &&
                        item.annotationType() == DefaultValue.class)
                .findFirst();
        return HBool.exec(
                null != anno && anno.isPresent(),
                anno::get,
                () -> null);
    }
}
