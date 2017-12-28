package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Up;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.exception.UpClassArgsException;
import io.vertx.up.exception.UpClassInvalidException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Anno;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroLauncher;
import io.vertx.up.web.anima.*;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application start information
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    private transient final Class<?> clazz;

    private transient ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();

    private VertxApplication(final Class<?> clazz) {
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
            new VertxApplication(clazz).run(args);
        }, LOGGER);
    }

    private void run(final Object... args) {

        final Launcher<Vertx> launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            /** 1.Find Agent for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(AgentScatter.class);
                scatter.connect(vertx);
            }, "agent-runner");

            /** 2.Find Worker for deploy **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(WorkerScatter.class);
                scatter.connect(vertx);
            }, "worker-runner");

            /** 3.Initialize Infix **/
            Runner.run(() -> {
                // Infix
                Scatter<Vertx> scatter = Instance.singleton(InfixScatter.class);
                scatter.connect(vertx);
                // Injection
                scatter = Instance.singleton(AffluxScatter.class);
                scatter.connect(vertx);
            }, "infix-afflux-runner");

            /** 4.Rule started **/
            Runner.run(() -> {
                final Scatter<Vertx> scatter = Instance.singleton(CodexScatter.class);
                scatter.connect(vertx);
            }, "codex-engine-runner");
        });
    }
}
