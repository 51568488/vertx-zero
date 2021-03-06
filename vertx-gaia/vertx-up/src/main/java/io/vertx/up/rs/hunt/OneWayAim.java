package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;

/**
 * OneWayAim: Event Bus: One-Way
 */
public class OneWayAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> Responser.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method callxx
            final Object returnValue = invoke(event, arguments);
            final Envelop request = Flower.continuous(context, returnValue);

            // 3. Build event bus
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            // 4. Send message
            final String address = address(event);
            bus.<Envelop>send(address, request, handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // One Way message
                    response = Envelop.ok();
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            });
        }, context, event), event);
    }
}
