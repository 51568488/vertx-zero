package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Aim;
import org.vie.fun.HNull;

/**
 * BlockAim: Non-Event Bus: One-Way
 */
public class BlockAim extends BaseAim implements Aim {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return HNull.get(() -> (context) -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            invoke(event, arguments);

            // 3. Resource model building
            final Envelop data = Envelop.ok();
            
            // 4. Process modal
            Answer.reply(context, data, event);
        }, event);
    }
}
