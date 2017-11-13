package io.vertx.zero.web;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.exception.up.PluginOptionException;
import io.vertx.zero.core.equip.HttpServerVisitor;
import io.vertx.zero.core.equip.ServerVisitor;
import io.vertx.zero.core.equip.UprightVisitor;
import io.vertx.zero.core.equip.VertxVisitor;
import org.vie.cv.em.YamlType;
import org.vie.fun.HBool;
import org.vie.fun.HTry;
import org.vie.util.Instance;
import org.vie.util.log.Annal;

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
            // Init for all plugin options.
            ZeroPlugin.init();
            return null;
        }, LOGGER);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getOptions(final String name) {
        final YamlType type = ZeroPlugin.getType(name);
        HBool.execUp(null == type, LOGGER,
                PluginOptionException.class,
                ZeroGrid.class, name);
        if (YamlType.OBJECT == type) {
            return (T) ZeroPlugin.getObject(name);
        } else {
            return (T) ZeroPlugin.getArray(name);
        }
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
