package io.vertx.up.rs;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Depot;

/**
 * JSR330 signal
 */
public interface Sentry {
    /**
     * @param depot
     * @return
     */
    Handler<RoutingContext> signal(final Depot depot);
}
