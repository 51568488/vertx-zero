package io.vertx.zero.func;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.rs.hunt.Answer;
import io.vertx.zero.func.lang.JcConsumer;

public class HWeb {

    public static void exec(final JcConsumer consumer,
                            final RoutingContext context,
                            final Event event) {
        try {
            consumer.exec();
        } catch (final WebException ex) {
            final Envelop envelop = Envelop.failure(ex);
            Answer.reply(context, envelop, event);
        }
    }
}
