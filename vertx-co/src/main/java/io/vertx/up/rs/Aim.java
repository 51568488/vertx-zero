package io.vertx.up.rs;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;

/**
 * Hunt to aim and select the objective
 */
public interface Aim {
    /**
     * @param event
     * @return
     */
    Handler<RoutingContext> attack(final Event event);
}
