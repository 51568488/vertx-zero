package io.vertx.up;

import com.vie.exception.up.UpClassArgsException;
import com.vie.exception.up.UpClassInvalidException;
import com.vie.fun.HBool;
import com.vie.fun.HTry;
import com.vie.util.Instance;
import com.vie.util.log.Annal;
import io.vertx.core.DeploymentOptions;
import io.vertx.up.annotations.Up;
import io.vertx.up.mirror.Anno;
import io.vertx.up.rs.VertxAnno;
import io.vertx.up.web.ZeroLauncher;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Vertx Application start information
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    private transient final Class<?> clazz;
    private ConcurrentMap<String, Annotation> annotationMap = new ConcurrentHashMap<>();
    private final transient Annotation vertxAnno;

    private VertxApplication(final Class<?> clazz) {
        // Must not null
        HBool.execUp(
                null == clazz,
                LOGGER,
                UpClassArgsException.class, getClass());
        this.clazz = clazz;
        this.annotationMap = Anno.get(clazz);
        this.vertxAnno = Anno.get(clazz, Up.class);
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

    public void run(final Object... args) {
        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            /** 1. Extract Routine classes **/
            final Set<Class<?>> routines = VertxAnno.getRoutines();
            System.out.println(routines.size());
            for (final Class<?> routine : routines) {
            }
        });
    }

    private DeploymentOptions buildAgent() {

        return null;
    }
}
