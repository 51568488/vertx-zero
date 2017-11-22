package io.vertx.up.web;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.EnvelopOld;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Actuator;
import io.vertx.up.rs.hunt.Answer;

public class ZeroResponser {

    public static void exec(final Actuator consumer,
                            final RoutingContext context,
                            final Event event) {
        try {
            consumer.execute();
        } catch (final WebException ex) {
            final EnvelopOld envelop = EnvelopOld.failure(ex);
            Answer.reply(context, envelop, event);
        }
    }
}
