package io.vertx.up.web;

import io.vertx.core.ClusterOptions;
import io.vertx.core.ServidorOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.equip.NodeVisitor;
import io.vertx.zero.marshal.equip.VertxVisitor;
import io.vertx.zero.marshal.micro.HttpServerVisitor;
import io.vertx.zero.marshal.micro.RpcServerVisitor;
import io.vertx.zero.marshal.micro.RxServerVisitor;
import io.vertx.zero.marshal.micro.ServerVisitor;

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
    private static final ConcurrentMap<Integer, ServidorOptions> RPC_OPTS =
            new ConcurrentHashMap<>();
    private static final ConcurrentMap<Integer, HttpServerOptions> RX_OPTS =
            new ConcurrentHashMap<>();
    private static ClusterOptions CLUSTER;

    static {
        Fn.safeZero(() -> {
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
            // Init for RxServerOptions
            if (RX_OPTS.isEmpty()) {
                final ServerVisitor<HttpServerOptions> visitor =
                        Instance.singleton(RxServerVisitor.class);
                RX_OPTS.putAll(visitor.visit());
            }
            // Init for RpxServerOptions
            if (RPC_OPTS.isEmpty()) {
                final ServerVisitor<ServidorOptions> visitor =
                        Instance.singleton(RpcServerVisitor.class);
                RPC_OPTS.putAll(visitor.visit());
            }
            // Init for all plugin options.
            ZeroAmbient.init();
        }, LOGGER);
    }

    public static ConcurrentMap<String, VertxOptions> getVertxOptions() {
        return VX_OPTS;
    }

    public static ConcurrentMap<Integer, HttpServerOptions> getServerOptions() {
        return SERVER_OPTS;
    }

    public static ConcurrentMap<Integer, HttpServerOptions> getRxOptions() {
        return RX_OPTS;
    }

    public static ConcurrentMap<Integer, ServidorOptions> getRpcOptions() {
        return RPC_OPTS;
    }

    public static ClusterOptions getClusterOption() {
        return CLUSTER;
    }
}
