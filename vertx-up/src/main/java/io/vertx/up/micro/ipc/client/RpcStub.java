package io.vertx.up.micro.ipc.client;

import io.vertx.core.Vertx;
import io.vertx.up.atom.flux.IpcData;

/**
 * Different implementation by type.
 */
public interface RpcStub {
    /**
     * Rpc Logical
     *
     * @param vertx
     * @param data
     */
    void send(Vertx vertx, IpcData data);
}
