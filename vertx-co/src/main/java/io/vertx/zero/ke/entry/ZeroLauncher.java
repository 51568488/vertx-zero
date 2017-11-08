package io.vertx.zero.ke.entry;

import io.vertx.core.ClusterOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.zero.ke.Launcher;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ZeroLauncher implements Launcher {

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

    private void startDeploy(final Vertx vertx) {
        // TODO: Vertx Deployment
    }

    private void startStandalone(final Consumer<Vertx> consumer) {
        eachOption((name, option) -> {
            // 1. Initialize vertx.
            final Vertx vertx = Vertx.vertx(option);
            consumer.accept(vertx);
        });
    }

    private void startCluster(final ClusterManager manager, final Consumer<Vertx> consumer) {
        eachOption((name, option) -> {
            Vertx.clusteredVertx(option, clustered -> {
                // 2. Async
                final Vertx vertx = clustered.result();
                manager.setVertx(vertx);
                consumer.accept(vertx);
            });
        });
    }

    private void eachOption(final BiConsumer<String, VertxOptions> consumer) {
        final ConcurrentMap<String, VertxOptions> vertxOptions
                = ZeroGrid.getVertxOptions();
        vertxOptions.forEach(consumer);
    }
}
