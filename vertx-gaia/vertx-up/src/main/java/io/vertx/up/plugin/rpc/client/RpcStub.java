package io.vertx.up.plugin.rpc.client;

import io.grpc.ManagedChannel;

public interface RpcStub {
    /**
     * Connect to remote by channel
     *
     * @param channel channel reference
     */
    void connect(ManagedChannel channel);
}
