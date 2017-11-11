package io.vertx.up.rs.router;

import io.vertx.ext.web.Route;
import io.vertx.up.ce.Event;

/**
 * Hub management for route
 */
public interface Hub {
    /**
     * Route mount
     *
     * @param route
     * @param event
     */
    void mount(Route route, Event event);
}
