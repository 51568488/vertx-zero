package io.vertx.up.rs;

/**
 * Axis to mount entity
 * 1. Router
 * 2. Route
 * 3. Event
 */
public interface Axis<Router> {
    /**
     * Router management entry
     *
     * @param router
     */
    void mount(Router router);
}
