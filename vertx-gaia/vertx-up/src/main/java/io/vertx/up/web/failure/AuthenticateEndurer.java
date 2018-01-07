package io.vertx.up.web.failure;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.kidd.Readible;
import io.vertx.up.rs.hunt.Answer;

/**
 * Common handler to handle failure
 */
public class AuthenticateEndurer implements Handler<RoutingContext> {

    public static Handler<RoutingContext> create() {
        return new AuthenticateEndurer();
    }

    private AuthenticateEndurer() {
    }

    @Override
    public void handle(final RoutingContext event) {
        if (event.failed()) {
            final Throwable ex = event.failure();
            if (ex instanceof WebException) {
                final WebException error = (WebException) ex;
                final Readible readible = Readible.get();
                readible.interpret(error);
                Answer.reply(event, Envelop.failure(error));
            } else {
                // Other exception found
                event.fail(ex);
            }
        } else {
            // Success, do not throw, continue to request
            event.next();
        }
    }
}
