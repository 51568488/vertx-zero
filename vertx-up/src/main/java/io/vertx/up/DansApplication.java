package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.annotations.Up;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Anno;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroLauncher;
import io.vertx.up.web.anima.DetectScatter;
import io.vertx.up.web.anima.PointScatter;
import io.vertx.up.web.anima.Scatter;
import io.vertx.zero.exception.RpcPreparingException;
import io.vertx.zero.exception.UpClassArgsException;
import io.vertx.zero.exception.UpClassInvalidException;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application start launcher for api gateway.
 * It's only used in Micro Service mode.
 */
public class DansApplication {

    private static final Annal LOGGER = Annal.get(DansApplication.class);

    private transient final Class<?> clazz;

    private transient ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();

    private DansApplication(final Class<?> clazz) {
        // Must not null
        Fn.flingUp(
                null == clazz,
                LOGGER,
                UpClassArgsException.class, getClass());
        this.clazz = clazz;
        this.annotationMap = Anno.get(clazz);
        // Must be invalid
        Fn.flingUp(
                !this.annotationMap.containsKey(Up.class.getName()),
                LOGGER,
                UpClassInvalidException.class, getClass(), clazz.getName());
    }

    public static void run(final Class<?> clazz, final Object... args) {
        Fn.shuntRun(() -> {
            // Run vertx application.
            new DansApplication(clazz).run(args);
        }, LOGGER);
    }

    private void run(final Object... args) {
        // Check etcd server status, IPC Only
        Fn.flingUp(!EtcdData.enabled(),
                LOGGER, RpcPreparingException.class, getClass());

        final Launcher<Vertx> launcher = Instance.singleton(ZeroLauncher.class);

        launcher.start(vertx -> {
            /** 1.Find Agent for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(PointScatter.class);
                scatter.connect(vertx);
            }, "gateway-runner");
            /** 2.Find Worker for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(DetectScatter.class);
                scatter.connect(vertx);
            }, "detect-runner");

        });
    }
}
