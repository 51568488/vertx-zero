package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Virtual;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.up.web.ZeroResponser;

public class AsyncAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> ZeroResponser.exec(() -> {
            // 1. Build Envelop
            final Envelop request = invoke(context, event);
            // 2. Build event bus
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            // 3. Send message
            final String address = address(event);
            bus.<Envelop>send(address, request, handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // Request - Response message
                    response = success(address, handler);
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            });
        }, context, event), event);
    }

    private Envelop invoke(final RoutingContext context,
                           final Event event) {
        final Object proxy = event.getProxy();
        // 1. Build Arguments
        final Object[] arguments = buildArgs(context, event);
        // Interface direct
        final Envelop invoked;
        if (Virtual.is(proxy)) {
            final JsonObject message = new JsonObject();
            for (int idx = 0; idx < arguments.length; idx++) {
                message.put(String.valueOf(idx), arguments[idx]);
            }
            // 2.1. Direct send arguments
            invoked = Envelop.success(message);
        } else {
            // 2.2. Method call
            final Object returnValue = invoke(event, arguments);
            invoked = Envelop.success(returnValue);
        }
        // 3. Envelop injection for User/Headers
        invoked.setHeaders(context.request().headers());
        invoked.setUser(context.user());
        return invoked;
    }
}
