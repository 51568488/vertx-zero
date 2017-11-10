package io.vertx.up.rs.executor;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;
import org.vie.util.Instance;
import org.vie.util.Jackson;
import org.vie.util.mirror.Anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class EventBusHandler implements Executor {

    @Override
    public void execute(final RoutingContext context,
                        final Event event) {
        // 1. Call action
        final Method method = event.getAction();
        // 2. Invoke method to return types
        final Class<?> returnType = method.getReturnType();
        if (Void.class == returnType || void.class == returnType) {
            // TODO: Exception when execute
        } else {
            final Object returnValue = Caller.invokeMethod(context, event);
            if (null == returnValue) {
                // TODO: Exception
            } else {
                final Annotation annotation = Anno.get(method, Address.class);
                final String address = Instance.invoke(annotation, "value");
                final String value = Jackson.serialize(returnValue);
                // Send message to address
                final Vertx vertx = context.vertx();
                final EventBus bus = vertx.eventBus();
                bus.<String>send(address, value, handler -> {
                    // Build response
                    if (handler.succeeded()) {
                        final String data = handler.result().body();
                        final HttpServerResponse response = context.response();
                        response.end(data);
                    }
                });
            }
        }
    }
}
