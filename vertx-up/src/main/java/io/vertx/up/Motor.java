package io.vertx.up;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EnvelopCodec;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.up.atom.Envelop;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.exception.VertxCallbackException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Statute;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.web.ZeroGrid;
import io.vertx.up.web.ZeroHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Start up tools shared in
 * Web Application & Rx Application
 */
public final class Motor {

    private static final Annal LOGGER = Annal.get(Motor.class);

    public static <T> void start(
            final Class<?> clazz,
            final Consumer<T> consumer,
            final Consumer<Consumer<T>> fnSingle,
            final BiConsumer<ClusterManager, Consumer<T>> fnCluster,
            final Annal logger) {
        if (null == consumer) {
            throw new VertxCallbackException(clazz);
        }
        // 1. Check if clustered mode
        final ClusterOptions cluster = ZeroGrid.getClusterOption();
        if (cluster.isEnabled()) {
            // 2.1. Clustered
            final ClusterManager manager = cluster.getManager();
            logger.info(Info.APP_CLUSTERD, manager.getClass().getName(),
                    manager.getNodeID(), manager.isActive());
            fnCluster.accept(manager, consumer);
        } else {
            // 2.2. Standalone
            fnSingle.accept(consumer);
        }
    }

    public static void each(
            final BiConsumer<String, VertxOptions> consumer) {
        final ConcurrentMap<String, VertxOptions> vertxOptions
                = ZeroGrid.getVertxOptions();
        vertxOptions.forEach(consumer);
    }

    public static void codec(final EventBus eventBus) {
        eventBus.registerDefaultCodec(Envelop.class,
                Instance.singleton(EnvelopCodec.class));
    }

    /**
     * Agent calculation
     *
     * @param defaultAgents
     * @param internals
     * @return
     */
    public static ConcurrentMap<ServerType, Class<?>> agents(
            final ServerType category,
            final Class<?>[] defaultAgents,
            final ConcurrentMap<ServerType, Class<?>> internals
    ) {
        final ConcurrentMap<ServerType, List<Class<?>>> agents =
                getMergedAgents(category, internals);
        final ConcurrentMap<ServerType, Boolean> defines =
                ZeroHelper.isAgentDefined(agents, defaultAgents);
        final ConcurrentMap<ServerType, Class<?>> ret =
                new ConcurrentHashMap<>();
        // Fix Boot
        // 1. If defined, use default
        Fn.itMap(agents, (type, list) -> {
            // 2. Defined -> You have defined
            Fn.safeSemi(defines.containsKey(type) && defines.get(type), LOGGER,
                    () -> {
                        // Use user-defined Agent instead.
                        final Class<?> found = Statute.findUnique(list,
                                (item) -> internals.get(type) != item);
                        if (null != found) {
                            ret.put(type, found);
                        }
                    }, () -> {
                        // Use internal defined ( system defaults )
                        final Class<?> found = Statute.findUnique(list,
                                (item) -> internals.get(type) == item);
                        if (null != found) {
                            ret.put(type, found);
                        }
                    });
        });
        return ret;
    }

    private static ConcurrentMap<ServerType, List<Class<?>>> getMergedAgents(
            final ServerType category,
            final ConcurrentMap<ServerType, Class<?>> internals
    ) {
        final ConcurrentMap<ServerType, List<Class<?>>> agents = ZeroAnno.getAgents();
        if (agents.isEmpty()) {
            // Inject ServerType by category input.
            agents.put(category, new ArrayList<>(internals.values()));
        }
        return agents;
    }
}
