package io.vertx.up.micro;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Common handler to handle failure
 */
public class HttpZeroEndurer implements Handler<RoutingContext> {

    public static Handler<RoutingContext> create() {
        return new HttpZeroEndurer();
    }

    private HttpZeroEndurer() {
    }

    @Override
    public void handle(final RoutingContext event) {
        System.out.println("Exception");
    }
}
