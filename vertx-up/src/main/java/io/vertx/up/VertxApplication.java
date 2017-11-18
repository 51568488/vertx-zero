package io.vertx.up;

import io.vertx.up.annotations.Up;
import io.vertx.up.exception.UpClassArgsException;
import io.vertx.up.exception.UpClassInvalidException;
import io.vertx.up.web.ZeroLauncher;
import io.vertx.up.web.anima.*;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.Runner;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application start information
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    private transient final Class<?> clazz;

    private ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();

    private VertxApplication(final Class<?> clazz) {
        // Must not null
        HBool.execUp(
                null == clazz,
                LOGGER,
                UpClassArgsException.class, getClass());
        this.clazz = clazz;
        this.annotationMap = Anno.get(clazz);
        // Must be invalid
        HBool.execUp(
                !this.annotationMap.containsKey(Up.class.getName()),
                LOGGER,
                UpClassInvalidException.class, getClass(), clazz.getName());
    }

    public static void run(final Class<?> clazz, final Object... args) {
        HTry.execUp(() -> {
            // Run vertx application.
            new VertxApplication(clazz).run(args);
        }, LOGGER);
    }

    private void run(final Object... args) {

        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            /** 1.Find Agent for deploy **/
            Runner.run(() -> {
                final Scatter scatter = Instance.singleton(AgentScatter.class);
                scatter.connect(vertx);
            }, "agent-runner");

            /** 2.Find Worker for deploy **/
            Runner.run(() -> {
                final Scatter scatter = Instance.singleton(WorkerScatter.class);
                scatter.connect(vertx);
            }, "worker-runner");

            /** 3.Initialize Infix **/
            Runner.run(() -> {
                final Scatter scatter = Instance.singleton(InfixScatter.class);
                scatter.connect(vertx);
            }, "infix-runner");

            /** 4.Injection system with JSR 330 **/
            Runner.run(() -> {
                final Scatter scatter = Instance.singleton(AffluxScatter.class);
                scatter.connect(vertx);
            }, "afflux-runner");
        });
    }
}
