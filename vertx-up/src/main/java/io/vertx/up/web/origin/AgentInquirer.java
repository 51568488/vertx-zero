package io.vertx.up.web.origin;

import io.vertx.up.annotations.Agent;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.web.ZeroHelper;
import io.vertx.zero.func.HPool;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @Agent
 */
public class AgentInquirer implements
        Inquirer<ConcurrentMap<ServerType, List<Class<?>>>> {

    @Override
    public ConcurrentMap<ServerType, List<Class<?>>> scan(final Set<Class<?>> classes) {
        final Set<Class<?>> agents =
                classes.stream()
                        .filter((item) -> item.isAnnotationPresent(Agent.class))
                        .collect(Collectors.toSet());
        return HPool.group(agents,
                ZeroHelper::getAgentKey,
                (item) -> item);
    }
}
