package io.vertx.up.rs.hunt;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.micro.ipc.client.TunnelClient;
import io.vertx.up.rs.Aim;

public class IpcAim extends BaseAim implements Aim<RoutingContext> {

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return Fn.get(() -> (context) -> Responser.exec(() -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            final Object result = invoke(event, arguments);

            // 3. Resource model building
            final Envelop data = Flower.continuous(context, result);

            // 4. Rpc Client Call to send the data.
            final Future<Envelop> handler = TunnelClient.create(getClass())
                    .connect(context.vertx())
                    .connect(event)
                    .send(data);
            // 5. Reply
            handler.setHandler(res -> Answer.reply(context, res.result()));
        }, context, event), event);
    }
}
