package io.vertx.up.rs.hunt;

import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.reflect.ParamFiller;
import org.vie.exception.WebException;
import org.vie.exception.web._500DeliveryErrorException;
import org.vie.exception.web._500EntityCastException;
import org.vie.fun.HNull;
import org.vie.util.Instance;
import org.vie.util.mirror.Anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class to provide template method
 */
public abstract class BaseAim {
    /**
     * Template method
     *
     * @param context
     * @param event
     * @return
     */
    protected Object[] buildArgs(final RoutingContext context,
                                 final Event event) {
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
        return arguments.toArray();
    }

    /**
     * Get event bus address.
     *
     * @param event
     * @return
     */
    protected String address(final Event event) {
        final Method method = event.getAction();
        final Annotation annotation = Anno.get(method, Address.class);
        return Instance.invoke(annotation, "value");
    }

    /**
     * @param event
     * @param args
     * @return
     */
    protected Object invoke(final Event event, final Object[] args) {
        final Method method = event.getAction();
        return Instance.invoke(event.getProxy(), method.getName(), args);
    }

    protected Envelop failure(final String address,
                              final AsyncResult<Message<Envelop>> handler) {
        final WebException error
                = new _500DeliveryErrorException(getClass(),
                address,
                HNull.get(handler.cause(), () -> handler.cause().getMessage(), null));
        return Envelop.failure(error);
    }

    protected Envelop success(final String address,
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
        return envelop;
    }

    
}
