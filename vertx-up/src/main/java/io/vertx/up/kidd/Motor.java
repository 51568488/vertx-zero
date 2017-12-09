package io.vertx.up.kidd;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EnvelopCodec;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.Info;
import io.vertx.up.exception.VertxCallbackException;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroGrid;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Start up tools shared in
 * Web Application & Rx Application
 */
public final class Motor {

    public static <T> void start(
            final Class<?> clazz,
            final Consumer<T> consumer,
            final Consumer<Consumer<T>> fnSingle,
            final BiConsumer<ClusterManager, Consumer<T>> fnCluster,
            final Annal logger) {
        if (null == consumer) {
            throw new VertxCallbackException(clazz);
        }
        // 1. Check if clustered mode
        final ClusterOptions cluster = ZeroGrid.getClusterOption();
        if (cluster.isEnabled()) {
            // 2.1. Clustered
            final ClusterManager manager = cluster.getManager();
            logger.info(Info.APP_CLUSTERD, manager.getClass().getName(),
                    manager.getNodeID(), manager.isActive());
            fnCluster.accept(manager, consumer);
        } else {
            // 2.2. Standalone
            fnSingle.accept(consumer);
        }
    }

    public static void each(
            final BiConsumer<String, VertxOptions> consumer) {
        final ConcurrentMap<String, VertxOptions> vertxOptions
                = ZeroGrid.getVertxOptions();
        vertxOptions.forEach(consumer);
    }

    public static void codec(final EventBus eventBus) {
        eventBus.registerDefaultCodec(Envelop.class,
                Instance.singleton(EnvelopCodec.class));
    }
}
