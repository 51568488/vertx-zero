package io.vertx.zero.marshal.equip;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.zero.marshal.Visitor;

import java.util.concurrent.ConcurrentMap;

/**
 * Vert.x instance to read configuration
 */
public interface NodeVisitor
        extends Visitor<ConcurrentMap<String, VertxOptions>> {

    String YKEY_OPTIONS = "options";

    String YKEY_CLUSTERED = "clustered";

    String YKEY_INSTANCE = "instance";

    String YKEY_NAME = "name";

    /**
     * Get cluster configuration from vertx initialization.
     *
     * @return
     */
    ClusterOptions getCluster();
}
