package io.vertx.zero.ke;

/**
 * Launcher:
 * 1. Cluster
 * 2. Vertx
 * 3. Verticle Deployment
 * 4. Router Deployment
 */
public interface Launcher {
    /**
     * Start
     */
    void start();

    /**
     * Stop
     */
    void stop();
}
