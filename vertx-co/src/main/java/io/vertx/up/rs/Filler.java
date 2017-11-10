package io.vertx.up.rs;

import io.vertx.ext.web.RoutingContext;

/**
 * Fill the arguments into reference list
 * as arguments
 */
public interface Filler {
    /**
     * @param name
     * @param paramType
     * @param datum
     * @return
     */
    Object apply(String name,
                 Class<?> paramType,
                 RoutingContext datum);
}
