package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Aim;
import org.vie.fun.HNull;
import org.vie.fun.HWeb;

/**
 * SyncAim: Non-Event Bus: Request-Response
 */
public class SyncAim extends BaseAim implements Aim {
    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return HNull.get(() -> (context) -> {
            HWeb.exec(() -> {
                // 1. Build Arguments
                final Object[] arguments = buildArgs(context, event);

                // 2. Method call
                final Object result = invoke(event, arguments);

                // 3. Resource model building
                final Envelop data = Envelop.success(result);

                // 4. Process modal
                Answer.reply(context, data, event);

            }, context, event);
        }, event);
    }
}
