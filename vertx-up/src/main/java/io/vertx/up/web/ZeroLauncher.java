package io.vertx.up.web;

import io.vertx.core.ClusterOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EnvelopCodec;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.up.Launcher;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.Info;
import io.vertx.up.exception.VertxCallbackException;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ZeroLauncher implements Launcher {

    private static final Annal LOGGER = Annal.get(ZeroLauncher.class);

    private static final ConcurrentMap<String, Vertx> VERTX = new ConcurrentHashMap<>();

    @Override
    public void start(final Consumer<Vertx> callback) {
        if (null == callback) {
            throw new VertxCallbackException(getClass());
        }

        // 1. Check if clustered mode
        final ClusterOptions cluster = ZeroGrid.getClusterOption();
        if (cluster.isEnabled()) {
            // 2.1.Clustered
            final ClusterManager manager = cluster.getManager();
            LOGGER.info(Info.APP_CLUSTERD, manager.getClass().getName(),
                    manager.getNodeID(), manager.isActive());
            startCluster(manager, callback);
        } else {
            // 2.2.Standalone
            startStandalone(callback);
        }
    }

    @Override
    public void stop(final Consumer<Vertx> callback) {
        // TODO: Wait for implementation
    }

    private void startStandalone(final Consumer<Vertx> consumer) {
        eachOption((name, option) -> {
            // 1. Standalone vertx initialized.
            final Vertx vertx = Vertx.vertx(option);
            // 2. Codecs
            registryCodec(vertx);
            // Finalized
            VERTX.putIfAbsent(name, vertx);
            consumer.accept(vertx);
        });
    }

    private void startCluster(final ClusterManager manager, final Consumer<Vertx> consumer) {
        eachOption((name, option) -> {
            Vertx.clusteredVertx(option, clustered -> {
                // 1. Async clustered vertx initialized
                final Vertx vertx = clustered.result();
                // 2. Codecs
                registryCodec(vertx);
                // 3. Cluster connect
                manager.setVertx(vertx);
                // Finalized
                VERTX.putIfAbsent(name, vertx);
                consumer.accept(vertx);
            });
        });
    }

    private void eachOption(final BiConsumer<String, VertxOptions> consumer) {
        final ConcurrentMap<String, VertxOptions> vertxOptions
                = ZeroGrid.getVertxOptions();
        vertxOptions.forEach(consumer);
    }

    private void registryCodec(final Vertx vertx) {
        vertx.eventBus().registerDefaultCodec(Envelop.class,
                Instance.singleton(EnvelopCodec.class));
    }
}
