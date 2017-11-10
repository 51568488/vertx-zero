package io.vertx.up.rs;

import io.vertx.ext.web.RoutingContext;

import java.util.List;

/**
 * Fill the arguments into reference list
 * as arguments
 */
public interface Filler {
    /**
     * @param reference
     * @param datum
     */
    void apply(List<Object> reference,
               RoutingContext datum);
}
