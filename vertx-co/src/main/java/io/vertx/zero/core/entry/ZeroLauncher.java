package io.vertx.zero.core.entry;

import io.vertx.core.ClusterOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.zero.core.Launcher;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

public class ZeroLauncher implements Launcher {

    private static final ConcurrentMap<String, Vertx> VERTX = new ConcurrentHashMap<>();

    @Override
    public void start() {
        // 1. Check if clustered mode
        final ClusterOptions cluster = ZeroGrid.getClusterOption();
        if (cluster.isEnabled()) {
            // 2.1.Clustered
            final ClusterManager manager = cluster.getManager();
            startCluster(manager, this::startDeploy);
        } else {
            // 2.2.Standalone
            startStandalone(this::startDeploy);
        }
    }

    @Override
    public void stop() {

    }

    private void startDeploy(final String name, final Vertx vertx) {
        // Before deploy, fill all the vertx map instances.
        VERTX.putIfAbsent(name, vertx);
        // TODO: Vertx Deployment

    }

    private void startStandalone(final BiConsumer<String, Vertx> consumer) {
        eachOption((name, option) -> {
            // 1. Initialize vertx.
            final Vertx vertx = Vertx.vertx(option);
            consumer.accept(name, vertx);
        });
    }

    private void startCluster(final ClusterManager manager, final BiConsumer<String, Vertx> consumer) {
        eachOption((name, option) -> {
            Vertx.clusteredVertx(option, clustered -> {
                // 2. Async
                final Vertx vertx = clustered.result();
                manager.setVertx(vertx);
                consumer.accept(name, vertx);
            });
        });
    }

    private void eachOption(final BiConsumer<String, VertxOptions> consumer) {
        final ConcurrentMap<String, VertxOptions> vertxOptions
                = ZeroGrid.getVertxOptions();
        vertxOptions.forEach(consumer);
    }
}
