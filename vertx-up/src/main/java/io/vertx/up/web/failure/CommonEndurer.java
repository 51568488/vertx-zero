package io.vertx.up.web.failure;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Common handler to handle failure
 */
public class CommonEndurer implements Handler<RoutingContext> {

    public static Handler<RoutingContext> create() {
        return new CommonEndurer();
    }

    private CommonEndurer() {
    }

    @Override
    public void handle(final RoutingContext event) {
        if (event.failed()) {
            System.out.println(event.data());
            event.failure().printStackTrace();
        }
    }
}
