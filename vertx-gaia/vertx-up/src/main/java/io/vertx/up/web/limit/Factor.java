package io.vertx.up.web.limit;

import io.vertx.up.eon.em.ServerType;

import java.util.concurrent.ConcurrentMap;

/**
 * Start Up condition for different verticle deployment.
 */
public interface Factor {
    /**
     * Filter
     *
     * @return
     */
    ConcurrentMap<ServerType, Class<?>> agents();
}
