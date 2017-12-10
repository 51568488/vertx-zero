package io.vertx.rx;

import io.vertx.rx.web.ZeroLauncher;
import io.vertx.up.annotations.Up;
import io.vertx.up.exception.UpClassArgsException;
import io.vertx.up.exception.UpClassInvalidException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Anno;
import io.vertx.up.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * RxJava Application start information
 */
public class RxApplication {

    private static final Annal LOGGER = Annal.get(RxApplication.class);

    private transient final Class<?> clazz;

    private ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();

    private RxApplication(final Class<?> clazz) {
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
            // Run Rx application.
            new RxApplication(clazz).run(args);
        }, LOGGER);
    }

    private void run(final Object... args) {

        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            
        });
    }
}
