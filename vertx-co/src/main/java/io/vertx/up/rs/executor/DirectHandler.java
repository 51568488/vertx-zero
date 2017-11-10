package io.vertx.up.rs.executor;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.vie.util.Instance;
import org.vie.util.Jackson;

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
        // 4. Invoke method to return types
        final Class<?> returnType = method.getReturnType();
        final HttpServerResponse response = context.response();
        if (Void.class == returnType || void.class == returnType) {
            // 4.1. Write to Client
            Instance.invoke(event.getProxy(), method.getName(),
                    arguments.toArray(new Object[]{}));
            response.end("OK");
        } else {
            final Object returnValue = Instance.invoke(event.getProxy(), method.getName(),
                    arguments.toArray(new Object[]{}));
            if (null != returnValue) {
                // TODO: Temp
                final String json = Jackson.serialize(returnValue);
                response.end(json);
            }
        }
    }
}
