package io.vertx.up;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.up.annotations.Up;
import io.vertx.up.cv.Message;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.VertxAnno;
import io.vertx.up.rs.config.AgentExtractor;
import io.vertx.up.web.HttpAgent;
import io.vertx.up.web.ZeroLauncher;
import org.vie.cv.em.ServerType;
import org.vie.exception.up.UpClassArgsException;
import org.vie.exception.up.UpClassInvalidException;
import org.vie.fun.HBool;
import org.vie.fun.HMap;
import org.vie.fun.HTry;
import org.vie.util.Instance;
import org.vie.util.Statute;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static io.vertx.up.cv.Message.AGENT_END;

/**
 * Vertx Application start information
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    private static final Class<?>[] DEFAULT_AGENTS = new Class<?>[]{
            HttpAgent.class
    };

    private static final ConcurrentMap<ServerType, Class<?>> INTERNALS
            = new ConcurrentHashMap<ServerType, Class<?>>() {
        {
            put(ServerType.HTTP, HttpAgent.class);
        }
    };

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

    public void run(final Object... args) {
        final Launcher launcher = Instance.singleton(ZeroLauncher.class);
        launcher.start(vertx -> {
            /** 1.Find Agent for deploy **/
            deployAgents(vertx);
            /** 2.Find Worker for deploy **/

            /** 4.Connect and started **/
        });
    }

    private void deployAgents(final Vertx vertx) {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = getAgents();
        final Extractor<DeploymentOptions> extractor =
                Instance.singleton(AgentExtractor.class);
        HMap.exec(agents, (type, clazz) -> {
            // 2.1 Agent deployment options
            final DeploymentOptions option = extractor.extract(clazz);
            // 2.2 Agent deployment
            final String name = clazz.getName();
            vertx.deployVerticle(name, option, (result) -> {
                // 2.3 Success or Failed.
                if (result.succeeded()) {
                    LOGGER.info(AGENT_END, name, option.getInstances(), result.result());
                } else {
                    LOGGER.info(Message.AGENT_FAIL, name, option.getInstances(), result.result(),
                            null == result.cause() ? null : result.cause().getMessage());
                }
            });
        });
    }

    /**
     * Find agent for each server type.
     *
     * @return
     */
    private ConcurrentMap<ServerType, Class<?>> getAgents() {
        final ConcurrentMap<ServerType, List<Class<?>>> agents =
                VertxAnno.getAgents();
        final ConcurrentMap<ServerType, Boolean> defines =
                VertxAnno.isDefined(agents, DEFAULT_AGENTS);
        final ConcurrentMap<ServerType, Class<?>> ret =
                new ConcurrentHashMap<>();
        // 1. If defined, use default
        HMap.exec(agents, (type, list) -> {
            // 2. Defined -> You have defined
            HBool.exec(defines.containsKey(type) && defines.get(type),
                    () -> {

                        // Use user-defined Agent instead.
                        final Class<?> found = Statute.findUnique(list,
                                (item) -> INTERNALS.get(type) != item);
                        if (null != found) {
                            LOGGER.info(Message.AGENT_DEFINED, found.getName(), type);
                            ret.put(type, found);
                        }
                        return null;
                    }, () -> {

                        // Use internal defined ( system defaults )
                        final Class<?> found = Statute.findUnique(list,
                                (item) -> INTERNALS.get(type) == item);
                        if (null != found) {
                            ret.put(type, found);
                        }
                        return null;
                    });
        });
        return ret;
    }
}
