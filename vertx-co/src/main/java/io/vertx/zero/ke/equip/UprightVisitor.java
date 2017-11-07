package io.vertx.zero.ke.equip;

import io.vertx.core.VertxOptions;
import io.vertx.zero.ke.Visitor;

import java.util.concurrent.ConcurrentMap;

/**
 * Vert.x instance to read configuration
 */
public interface UprightVisitor
        extends Visitor<ConcurrentMap<String, VertxOptions>> {
}
