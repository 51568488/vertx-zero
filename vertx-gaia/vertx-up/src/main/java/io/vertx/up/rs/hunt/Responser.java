package io.vertx.up.rs.hunt;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Actuator;

class Responser {

    public static void exec(final Actuator consumer,
                            final RoutingContext context,
                            final Event event) {
        try {
            consumer.execute();
        } catch (final WebException ex) {
            final Envelop envelop = Envelop.failure(ex);
            Answer.reply(context, envelop, event);
        }
    }
}
