package org.vie.fun;

import io.vertx.exception.WebException;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.hunt.Answer;
import org.vie.fun.lang.JcConsumer;

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
