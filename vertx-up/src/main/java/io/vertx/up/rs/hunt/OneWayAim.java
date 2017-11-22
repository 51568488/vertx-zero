package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.EnvelopOld;
import io.vertx.up.atom.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.up.web.ZeroResponser;

/**
 * OneWayAim: Event Bus: One-Way
 */
public class OneWayAim extends BaseAim implements Aim {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> ZeroResponser.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            final Object returnValue = invoke(event, arguments);
            final EnvelopOld request = EnvelopOld.success(returnValue);

            // 3. Build event bus
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            // 4. Send message
            final String address = address(event);
            bus.<EnvelopOld>send(address, request, handler -> {
                final EnvelopOld response;
                if (handler.succeeded()) {
                    // One Way message
                    response = EnvelopOld.ok();
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            });
        }, context, event), event);
    }
}
