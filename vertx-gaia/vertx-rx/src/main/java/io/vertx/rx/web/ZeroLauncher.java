package io.vertx.rx.web;

import io.reactivex.Single;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.reactivex.core.Vertx;
import io.vertx.up.Launcher;
import io.vertx.up.boot.Motor;
import io.vertx.up.log.Annal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

public class ZeroLauncher implements Launcher<Vertx> {

    private static final Annal LOGGER = Annal.get(ZeroLauncher.class);

    private static final ConcurrentMap<String, Vertx> VERTX = new ConcurrentHashMap<>();

    @Override
    public void start(final Consumer<Vertx> callback) {
        Motor.start(getClass(),
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
        Motor.each((name, option) -> {

            final Vertx vertx = Vertx.vertx(option);

            Motor.codec(vertx.eventBus().getDelegate());

            VERTX.putIfAbsent(name, vertx);
            consumer.accept(vertx);
        });
    }

    private void startCluster(final ClusterManager manager,
                              final Consumer<Vertx> consumer) {
        Motor.each((name, option) -> {
            // Set cluster manager
            option.setClusterManager(manager);

            final Single<Vertx> observable = Vertx.rxClusteredVertx(option);
            observable.subscribe(vertx -> {
                Motor.codec(vertx.eventBus().getDelegate());
                // Finalized
                VERTX.putIfAbsent(name, vertx);
                consumer.accept(vertx);
            });
        });
    }
}
