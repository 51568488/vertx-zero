package io.vertx.up.rs.executor;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.vie.util.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Non-Event Bus Mode
 * Request workflow：
 * 1. Pre process request by container
 * 2. Scan event action about parameters
 * 3. Execute event action
 * 4. Get response data and send response directly.
 */
public class DirectHandler implements Executor {

    @Override
    public void execute(final RoutingContext context,
                        final Event event) {
        // 1. Call action
        final Method method = event.getAction();
        final List<Object> arguments = new ArrayList<>();

        // 2. Extract definition from method
        final Class<?>[] parameterTypes = method.getParameterTypes();
        final Annotation[][] annotations = method.getParameterAnnotations();
        for (int idx = 0; idx < parameterTypes.length; idx++) {

            // 3. Process filler to build parameters.
            arguments.add(ArgsFiller.process(context,
                    parameterTypes[idx], annotations[idx]));
        }
        // 3. Invoke method to return types
        System.out.println("call");
        Instance.invoke(event.getProxy(), method.getName(), "Lang");
    }
}
