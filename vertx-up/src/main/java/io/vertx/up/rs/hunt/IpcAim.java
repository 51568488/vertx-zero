package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.micro.ipc.TunnelClient;
import io.vertx.up.rs.Aim;
import io.vertx.up.web.ZeroResponser;

public class IpcAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> ZeroResponser.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            final Object result = invoke(event, arguments);

            // 3. Resource model building
            final Envelop data = Envelop.success(result);

            // 4. Rpc Client Call to send the data.
            TunnelClient.create(getClass())
                    .connect(context.vertx())
                    .connect(event)
                    .send(data);
        }, context, event), event);
    }
}
