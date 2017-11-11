package io.vertx.up.rs.reflect;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import org.vie.util.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Help to call method directly
 */
public class Invoker {

    public static Object invokeMethod(final RoutingContext context, final Event event) {
        // 1. Call action
        final Method method = event.getAction();
        final List<Object> arguments = new ArrayList<>();

        // 2. Extract definition from method
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Annotation[][] annotations = method.getParameterAnnotations();
        for (int idx = 0; idx < parameterTypes.length; idx++) {

            // 3. Process filler to build parameters.
            arguments.add(ParamFiller.process(context,
                    parameterTypes[idx], annotations[idx]));
        }
        return Instance.invoke(event.getProxy(), method.getName(),
                arguments.toArray(new Object[]{}));
    }

    public static void invokeVoid(final RoutingContext context, final Event event) {
        invokeMethod(context, event);
    }
}
