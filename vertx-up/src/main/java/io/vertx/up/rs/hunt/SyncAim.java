package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.zero.func.Responser;

/**
 * SyncAim: Non-Event Bus: Request-Response
 */
public class SyncAim extends BaseAim implements Aim {
    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> {
            Responser.exec(() -> {
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
