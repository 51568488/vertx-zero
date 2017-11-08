package io.vertx.up.web;

import com.vie.hoc.HTry;
import com.vie.log.Annal;
import com.vie.util.Instance;
import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.zero.core.equip.HttpServerVisitor;
import io.vertx.zero.core.equip.ServerVisitor;
import io.vertx.zero.core.equip.UprightVisitor;
import io.vertx.zero.core.equip.VertxVisitor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Resource Scan for yml configuration, Loaded once
 */
class ZeroGrid {

    private static final Annal LOGGER = Annal.get(ZeroGrid.class);
    private static final ConcurrentMap<String, VertxOptions> VX_OPTS =
            new ConcurrentHashMap<>();
    private static final ConcurrentMap<Integer, HttpServerOptions> SERVER_OPTS =
            new ConcurrentHashMap<>();
    private static ClusterOptions CLUSTER;

    static {
        HTry.execZero(() -> {
            // Init for VertxOptions, ClusterOptions
            // 1. Visit Vertx
            if (VX_OPTS.isEmpty() || null == CLUSTER) {
                final UprightVisitor visitor =
                        Instance.singleton(VertxVisitor.class);
                VX_OPTS.putAll(visitor.visit());
                // 2. Must after visit
                CLUSTER = visitor.getCluster();
            }
            // Init for HttpServerOptions
            if (SERVER_OPTS.isEmpty()) {
                final ServerVisitor<HttpServerOptions> visitor =
                        Instance.singleton(HttpServerVisitor.class);
                SERVER_OPTS.putAll(visitor.visit());
            }
            return null;
        }, LOGGER);
    }

    public static ConcurrentMap<String, VertxOptions> getVertxOptions() {
        return VX_OPTS;
    }

    public static ConcurrentMap<Integer, HttpServerOptions> getServerOptions() {
        return SERVER_OPTS;
    }

    public static ClusterOptions getClusterOption() {
        return CLUSTER;
    }
}
