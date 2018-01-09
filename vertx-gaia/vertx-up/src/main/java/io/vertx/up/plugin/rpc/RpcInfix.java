package io.vertx.up.plugin.rpc;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.plugin.Infix;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Rpc Client for specific
 */
@Plugin
@SuppressWarnings("unchecked")
public class RpcInfix implements Infix {

    private static final String NAME = "ZERO_RPC_POOL";

    private static final ConcurrentMap<String, RpcClient> CLIENTS
            = new ConcurrentHashMap<>();


    private static void initInternal(final Vertx vertx,
                                     final String name) {
        Fn.pool(CLIENTS, name,
                () -> Infix.init(Plugins.Infix.RPC,
                        (config) -> RpcClient.createShared(vertx, config, name),
                        RpcInfix.class));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public RpcClient get() {
        return getClient();
    }

    public static RpcClient getClient() {
        return CLIENTS.get(NAME);
    }
}
