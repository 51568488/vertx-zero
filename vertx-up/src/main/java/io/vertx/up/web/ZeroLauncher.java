package io.vertx.up.web;

import io.vertx.core.Vertx;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.up.Launcher;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Boujour;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

public class ZeroLauncher implements Launcher {

    private static final Annal LOGGER = Annal.get(ZeroLauncher.class);

    private static final ConcurrentMap<String, Vertx> VERTX = new ConcurrentHashMap<>();

    @Override
    public void start(final Consumer<Vertx> callback) {
        Boujour.start(getClass(),
                callback,
                this::startStandalone,
                this::startCluster,
                LOGGER);
    }

    @Override
    public void stop(final Consumer<Vertx> callback) {
        // TODO: Wait for implementation
    }

    private void startStandalone(final Consumer<Vertx> consumer) {
        Boujour.each((name, option) -> {
            final Vertx vertx = Vertx.vertx(option);

            Boujour.codec(vertx.eventBus());

            VERTX.putIfAbsent(name, vertx);
            consumer.accept(vertx);
        });
    }

    private void startCluster(final ClusterManager manager,
                              final Consumer<Vertx> consumer) {
        Boujour.each((name, option) -> {
            Vertx.clusteredVertx(option, clustered -> {
                // 1. Async clustered vertx initialized
                final Vertx vertx = clustered.result();
                // 2. Codecs
                Boujour.codec(vertx.eventBus());
                // 3. Cluster connect
                manager.setVertx(vertx);
                // Finalized
                VERTX.putIfAbsent(name, vertx);

                consumer.accept(vertx);
            });
        });
    }
}
