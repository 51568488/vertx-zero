package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Common handler to handle failure
 */
public class FailureEndurer implements Handler<RoutingContext> {

    public static Handler<RoutingContext> create() {
        return new FailureEndurer();
    }

    private FailureEndurer() {
    }

    @Override
    public void handle(final RoutingContext event) {
        if (event.failed()) {
            final Throwable ex = event.failure();
            ex.printStackTrace();
        }
    }
}
