package io.vertx.up.rs;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;

/**
 * Executor for different workflow
 */
public interface Executor {

    void execute(final RoutingContext context,
                 final Event event);
}
