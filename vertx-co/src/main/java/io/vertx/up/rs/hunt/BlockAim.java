package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.rs.Aim;
import io.vertx.zero.func.HNull;
import io.vertx.zero.func.HWeb;

/**
 * BlockAim: Non-Event Bus: One-Way
 */
public class BlockAim extends BaseAim implements Aim {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return HNull.get(() -> (context) -> {
            HWeb.exec(() -> {
                // 1. Build Arguments
                final Object[] arguments = buildArgs(context, event);

                // 2. Method call
                invoke(event, arguments);

                // 3. Resource model building
                final Envelop data = Envelop.ok();

                // 4. Process modal
                Answer.reply(context, data, event);
            }, context, event);
        }, event);
    }
}
