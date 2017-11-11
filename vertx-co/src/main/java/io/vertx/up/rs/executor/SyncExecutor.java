package io.vertx.up.rs.executor;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.vie.util.Jackson;

import java.lang.reflect.Method;

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

        // 2. Invoke method to return types
        final Class<?> returnType = method.getReturnType();
        final HttpServerResponse response = context.response();
        if (Void.class == returnType || void.class == returnType) {

            // 3. Write to Client
            Caller.invokeVoid(context, event);
            response.end("OK");
        } else {
            final Object returnValue = Caller.invokeMethod(context, event);
            if (null != returnValue) {
                // TODO: Temp
                final String json = Jackson.serialize(returnValue);
                response.end(json);
            }
        }
    }
}
