package io.vertx.up.web.limit;

import io.vertx.up.boot.Motor;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.micro.ZeroApiAgent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This factor could start following:
 * 1. Http Api Gateway
 * 2. Rx/Http mode shared.
 */
public class ApiFactor implements Factor {

    private static final Class<?>[] DEFAULT_AGENTS = new Class<?>[]{
            ZeroApiAgent.class
    };

    private static final ConcurrentMap<ServerType, Class<?>> INTERNALS
            = new ConcurrentHashMap<ServerType, Class<?>>() {
        {
            put(ServerType.API, ZeroApiAgent.class);
        }
    };

    @Override
    public ConcurrentMap<ServerType, Class<?>> agents() {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = Motor.agents(ServerType.HTTP, DEFAULT_AGENTS, INTERNALS);
        /** 2. Filter invalid agents. **/
        final Set<ServerType> scanned = new HashSet<>(agents.keySet());
        final Set<ServerType> keeped = INTERNALS.keySet();
        scanned.removeAll(keeped);
        scanned.forEach(agents::remove);
        return agents;
    }
}
