package io.vertx.up.rs;

/**
 * Hub to mount entity
 * 1. Router
 * 2. Route
 * 3. Event
 *
 * @param <T>
 */
public interface Hub<T> {

    void mount(T entity);
}
