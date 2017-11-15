package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.func.HWeb;
import io.vertx.up.rs.Aim;
import io.vertx.zero.func.HNull;

public class AsyncAim extends BaseAim implements Aim {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return HNull.get(() -> (context) -> {
            HWeb.exec(() -> {
                // 1. Build Arguments
                final Object[] arguments = buildArgs(context, event);
                // 2. Method call
                final Object returnValue = invoke(event, arguments);
                final Envelop request = Envelop.success(returnValue);

                // 3. Build event bus
                final Vertx vertx = context.vertx();
                final EventBus bus = vertx.eventBus();
                // 4. Send message
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
            }, context, event);
        }, event);
    }
}
