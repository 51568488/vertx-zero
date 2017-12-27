package io.vertx.up.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.Motor;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.exception.RpcPreparingException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ZeroHttpAgent;
import io.vertx.up.micro.ZeroRpcAgent;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.AgentExtractor;
import io.vertx.up.tool.mirror.Instance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Agent scatter to deploy agents
 */
public class AgentScatter implements Scatter<Vertx> {

    private static final Annal LOGGER = Annal.get(AgentScatter.class);

    private static final Class<?>[] DEFAULT_AGENTS = new Class<?>[]{
            ZeroHttpAgent.class, ZeroRpcAgent.class
    };

    private static final ConcurrentMap<ServerType, Class<?>> INTERNALS
            = new ConcurrentHashMap<ServerType, Class<?>>() {
        {
            put(ServerType.HTTP, ZeroHttpAgent.class);
            put(ServerType.IPC, ZeroRpcAgent.class);
        }
    };

    @Override
    public void connect(final Vertx vertx) {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = Motor.agents(ServerType.HTTP, DEFAULT_AGENTS, INTERNALS);
        final Extractor<DeploymentOptions> extractor =
                Instance.instance(AgentExtractor.class);
        if (agents.containsKey(ServerType.IPC)) {
            // 2. Check etcd server status
            Fn.flingUp(!EtcdData.enabled(),
                    LOGGER, RpcPreparingException.class, getClass());
        }
        final ConcurrentMap<Class<?>, DeploymentOptions> options =
                new ConcurrentHashMap<>();
        Fn.itMap(agents, (type, clazz) -> {
            // Agent for non-rx ( Reactive Java 2 will be another mode )
            if (type != ServerType.RX) {
                // 3.1 Agent deployment options
                final DeploymentOptions option = extractor.extract(clazz);
                options.put(clazz, option);
                // 3.2 Agent deployment
                Verticles.deploy(vertx, clazz, option, LOGGER);
            }
        });
        // Runtime hooker
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Fn.itMap(agents, (type, clazz) -> {
                if (type != ServerType.RX) {
                    // 4. Undeploy Agent.
                    final DeploymentOptions opt = options.get(clazz);
                    Verticles.undeploy(vertx, clazz, opt, LOGGER);
                }
            });
        }));
    }
}
