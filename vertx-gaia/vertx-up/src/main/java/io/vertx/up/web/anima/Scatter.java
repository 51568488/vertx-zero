package io.vertx.up.web.anima;

/**
 * Child component works
 */
public interface Scatter<Vertx> {
    /**
     * Connect to vert.x to execute start up works.
     *
     * @param vertx common vertx.
     */
    void connect(Vertx vertx);
}
