package io.vertx.up.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.micro.ZeroHttpAgent;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.AgentExtractor;
import io.vertx.up.web.ZeroAnno;
import io.vertx.up.web.ZeroHelper;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HMap;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.Statute;
import io.vertx.zero.tool.mirror.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Agent scatter to deploy agents
 */
public class AgentScatter implements Scatter {

    private static final Annal LOGGER = Annal.get(AgentScatter.class);

    private static final Class<?>[] DEFAULT_AGENTS = new Class<?>[]{
            ZeroHttpAgent.class
    };

    private static final ConcurrentMap<ServerType, Class<?>> INTERNALS
            = new ConcurrentHashMap<ServerType, Class<?>>() {
        {
            put(ServerType.HTTP, ZeroHttpAgent.class);
        }
    };

    @Override
    public void connect(final Vertx vertx) {
/** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = getAgents();
        final Extractor<DeploymentOptions> extractor =
                Instance.instance(AgentExtractor.class);

        HMap.exec(agents, (type, clazz) -> {
            // 2.1 Agent deployment options
            final DeploymentOptions option = extractor.extract(clazz);
            // 2.2 Agent deployment
            Verticles.deploy(vertx, clazz, option, LOGGER);
        });
    }

    private ConcurrentMap<ServerType, List<Class<?>>> getMergedAgents() {
        final ConcurrentMap<ServerType, List<Class<?>>> agents = ZeroAnno.getAgents();
        if (agents.isEmpty()) {
            // Inject Http
            agents.put(ServerType.HTTP, new ArrayList<>(INTERNALS.values()));
        }
        return agents;
    }

    private ConcurrentMap<ServerType, Class<?>> getAgents() {
        final ConcurrentMap<ServerType, List<Class<?>>> agents =
                getMergedAgents();
        final ConcurrentMap<ServerType, Boolean> defines =
                ZeroHelper.isAgentDefined(agents, DEFAULT_AGENTS);
        final ConcurrentMap<ServerType, Class<?>> ret =
                new ConcurrentHashMap<>();
        // Fix Boot
        // 1. If defined, use default
        HMap.exec(agents, (type, list) -> {
            // 2. Defined -> You have defined
            HBool.exec(defines.containsKey(type) && defines.get(type),
                    () -> {

                        // Use user-defined Agent instead.
                        final Class<?> found = Statute.findUnique(list,
                                (item) -> INTERNALS.get(type) != item);
                        if (null != found) {
                            LOGGER.info(Info.AGENT_DEFINED, found.getName(), type);
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
