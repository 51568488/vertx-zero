package io.vertx.up.micro;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Common handler to handle failure
 */
public class ZeroHttpEndurer implements Handler<RoutingContext> {

    public static Handler<RoutingContext> create() {
        return new ZeroHttpEndurer();
    }

    private ZeroHttpEndurer() {
    }

    @Override
    public void handle(final RoutingContext event) {
        if (event.failed()) {
            final Throwable ex = event.failure();
            ex.printStackTrace();
        }
    }
}
