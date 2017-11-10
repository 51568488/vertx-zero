package io.vertx.up.rs;

import io.vertx.ext.web.Router;

/**
 * Hub to mount entity
 * 1. Router
 * 2. Route
 * 3. Event
 */
public interface Hub {

    void mount(Router router);
}
