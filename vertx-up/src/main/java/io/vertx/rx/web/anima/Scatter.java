package io.vertx.rx.web.anima;

import io.vertx.reactivex.core.Vertx;

/**
 * Child component works
 */
public interface Scatter {
    /**
     * Connect to vert.x to execute start up works.
     *
     * @param vertx reactivex vertx
     */
    void connect(Vertx vertx);
}
