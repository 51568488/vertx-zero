package io.vertx.up.web;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.marshal.equip.HttpServerVisitor;
import io.vertx.zero.marshal.equip.NodeVisitor;
import io.vertx.zero.marshal.equip.ServerVisitor;
import io.vertx.zero.marshal.equip.VertxVisitor;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Resource Pack for yml configuration, Loaded once
 */
public class ZeroGrid {

    private static final Annal LOGGER = Annal.get(ZeroGrid.class);
    private static final ConcurrentMap<String, VertxOptions> VX_OPTS =
            new ConcurrentHashMap<>();
    private static final ConcurrentMap<Integer, HttpServerOptions> SERVER_OPTS =
            new ConcurrentHashMap<>();
    private static ClusterOptions CLUSTER;

    static {
        HTry.execZero(() -> {
            // Init for VertxOptions, ClusterOptions
            // Visit Vertx
            if (VX_OPTS.isEmpty() || null == CLUSTER) {
                final NodeVisitor visitor =
                        Instance.singleton(VertxVisitor.class);
                VX_OPTS.putAll(visitor.visit());
                // Must after visit
                CLUSTER = visitor.getCluster();
            }
            // Init for HttpServerOptions
            if (SERVER_OPTS.isEmpty()) {
                final ServerVisitor<HttpServerOptions> visitor =
                        Instance.singleton(HttpServerVisitor.class);
                SERVER_OPTS.putAll(visitor.visit());
            }
            // Init for all plugin options.
            ZeroAmbient.init();
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
