package io.vertx.up.rs.executor;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Event;
import io.vertx.up.ce.codec.EnvelopCodec;
import io.vertx.up.rs.Executor;
import org.vie.exception.WebException;
import org.vie.exception.web._500DeliveryErrorException;
import org.vie.exception.web._500EntityCastException;
import org.vie.fun.HNull;
import org.vie.util.Instance;
import org.vie.util.mirror.Anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventBusExecutor implements Executor {

    private final transient AtomicBoolean isRegistered = new AtomicBoolean(false);

    @Override
    public void execute(final RoutingContext context,
                        final Event event) {
        // 1. Call action
        final Method method = event.getAction();
        // 2. Invoke method to return types
        final Class<?> returnType = method.getReturnType();
        final Annotation annotation = Anno.get(method, Address.class);

        final String address = Instance.invoke(annotation, "value");

        final Envelop envelop;
        if (Void.class == returnType || void.class == returnType) {
            // 3. If returnType is void, only call it and then build empty body Envelop
            envelop = Envelop.success(null);
        } else {
            final Object returnValue = Caller.invokeMethod(context, event);
            envelop = Envelop.success(returnValue);
        }

        // 4.Send message to address
        final Vertx vertx = context.vertx();
        final EventBus bus = vertx.eventBus();
        final HttpServerResponse response = context.response();
        // Set default codecs
        {
            // Fix issue: java.lang.IllegalStateException:
            // Already a default codec registered for class class io.vertx.up.ce.Envelop
            if (!this.isRegistered.getAndSet(true)) {
                bus.registerDefaultCodec(Envelop.class,
                        Instance.singleton(EnvelopCodec.class));
            }
        }
        bus.<Envelop>send(address, envelop, handler -> {
            // Prepare response
            Normalizer.out(response, event);

            // Build response
            if (handler.succeeded()) {

                // Successfully to send response to client
                fireSuccess(address, response, handler);
            } else {

                // Error detected and then send error message to client.
                fireFailure(address, response, handler);
            }
        });
    }

    private void fireFailure(final String address,
                             final HttpServerResponse response,
                             final AsyncResult<Message<Envelop>> handler) {
        final WebException error
                = new _500DeliveryErrorException(getClass(),
                address, HNull.get(handler.cause(), () -> handler.cause().getMessage(), null));
        final Envelop envelop = Envelop.failure(error);
        if (!response.ended()) {
            response.end(envelop.getResponse());
        }
    }

    private void fireSuccess(final String address,
                             final HttpServerResponse response,
                             final AsyncResult<Message<Envelop>> handler
    ) {
        Envelop envelop;
        try {
            final Message<Envelop> message = handler.result();
            envelop = message.body();
        } catch (final Throwable ex) {
            final WebException error
                    = new _500EntityCastException(getClass(),
                    address, ex.getMessage());
            envelop = Envelop.failure(error);
        }
        if (!response.ended()) {
            response.end(envelop.getResponse());
        }
    }
}
