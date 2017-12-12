package io.vertx.rx.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.reactivex.core.Vertx;
import io.vertx.rx.micro.ZeroRxAgent;
import io.vertx.up.Motor;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.AgentExtractor;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.anima.Scatter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AgentScatter implements Scatter<Vertx> {

    private static final Annal LOGGER = Annal.get(AgentScatter.class);

    private static final Class<?>[] DEFAULT_AGENTS = new Class<?>[]{
            ZeroRxAgent.class
    };

    private static final ConcurrentMap<ServerType, Class<?>> INTERNALS
            = new ConcurrentHashMap<ServerType, Class<?>>() {
        {
            put(ServerType.RX, ZeroRxAgent.class);
        }
    };

    @Override
    public void connect(final Vertx vertx) {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = Motor.agents(ServerType.RX, DEFAULT_AGENTS, INTERNALS);
        final Extractor<DeploymentOptions> extractor =
                Instance.instance(AgentExtractor.class);

        Fn.itMap(agents, (type, clazz) -> {
            if (ServerType.RX == type) {
                // 2.1. Agent deployment options
                final DeploymentOptions option = extractor.extract(clazz);
                // 2.2. Agent deployment
                Verticles.deploy(vertx, clazz, option, LOGGER);
            }
        });
    }
}
