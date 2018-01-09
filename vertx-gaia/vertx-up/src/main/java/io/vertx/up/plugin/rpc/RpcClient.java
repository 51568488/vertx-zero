package io.vertx.up.plugin.rpc;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

/**
 * IpcClient for zero of vertx
 *
 * @author Lang
 */
public interface RpcClient {
    /**
     * Default pool name
     */
    String DEFAULT_POOL_NAME = "DEFAULT_POOL";

    /**
     * @param vertx  the Vert.x instance
     * @param config the configuration
     * @return the client
     */
    static RpcClient createNonShared(final Vertx vertx, final JsonObject config) {
        return new RpcClientImpl(vertx, config, UUID.randomUUID().toString());
    }

    /**
     * @param vertx  the Vert.x instance
     * @param config the configuration
     * @param name   the name
     * @return the client
     */
    static RpcClient createShared(final Vertx vertx, final JsonObject config, final String name) {
        return new RpcClientImpl(vertx, config, name);
    }

    static RpcClient createShared(final Vertx vertx, final JsonObject config) {
        return new RpcClientImpl(vertx, config, DEFAULT_POOL_NAME);
    }

    /**
     * @param config  send target information
     *                {
     *                "name":"Service Name",
     *                "addr":"Target Rpc Address",
     *                "type": Default is UNITY
     *                }
     * @param data    send the data
     * @param handler async handler
     * @param <T>     return type
     * @return Rpc client
     */
    @Fluent
    <T> RpcClient connect(JsonObject config,
                          JsonObject data,
                          Handler<AsyncResult<T>> handler);

    /**
     * Only support Unity Type
     *
     * @param name    service name
     * @param address service address
     * @param data    JsonObject data
     * @param handler async handler
     * @param <T>     return type
     * @return Rpc client
     */
    @Fluent
    <T> RpcClient connect(final String name,
                          final String address,
                          final JsonObject data,
                          Handler<AsyncResult<T>> handler);
}
