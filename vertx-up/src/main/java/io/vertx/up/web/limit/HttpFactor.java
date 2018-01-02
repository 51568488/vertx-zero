package io.vertx.up.web.limit;

import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.Motor;
import io.vertx.up.eon.em.ServerType;
import io.vertx.zero.exception.RpcPreparingException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ZeroHttpAgent;
import io.vertx.up.micro.ZeroRpcAgent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * This factor could start following:
 * 1. Http Server,
 * 2. Rpc Server.
 */
public class HttpFactor implements Factor {

    private static final Annal LOGGER = Annal.get(HttpFactor.class);

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
    public ConcurrentMap<ServerType, Class<?>> agents() {
        /** 1.Find Agent for deploy **/
        final ConcurrentMap<ServerType, Class<?>> agents
                = Motor.agents(ServerType.HTTP, DEFAULT_AGENTS, INTERNALS);
        if (agents.containsKey(ServerType.IPC)) {
            // 2. Check etcd server status, IPC Only
            Fn.flingUp(!EtcdData.enabled(),
                    LOGGER, RpcPreparingException.class, getClass());
        }
        // 3. Filter invalid agents
        final Set<ServerType> scanned = new HashSet<>(agents.keySet());
        final Set<ServerType> keeped = INTERNALS.keySet();
        scanned.removeAll(keeped);
        scanned.forEach(agents::remove);
        return agents;
    }
}
