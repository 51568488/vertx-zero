package io.vertx.up.web.anima;

import io.vertx.core.Vertx;

/**
 * Child component works
 */
public interface Scatter {
    /**
     * Connect to vert.x to execute start up works.
     *
     * @param vertx
     */
    void connect(Vertx vertx);
}
