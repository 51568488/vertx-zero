package io.vertx.up.plugin.rpc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.micro.ipc.DataEncap;
import io.vertx.up.plugin.rpc.RpcRepdor;

/**
 * Used by rpc client.
 */
public class UnityStub implements RpcStub {

    private final transient ManagedChannel channel;

    public UnityStub(final ManagedChannel channel) {
        this.channel = channel;
    }

    @Override
    public Future<JsonObject> traffic(final IpcData data) {

        final UnityServiceGrpc.UnityServiceVertxStub stub
                = UnityServiceGrpc.newVertxStub(this.channel);
        // Request
        final IpcRequest request = DataEncap.in(data);
        // Call and return to future
        final Future<JsonObject> handler = Future.future();
        stub.unityCall(request, response ->
                // Reply
                RpcRepdor.create(this.getClass()).replyJson(handler, response));
        return handler;
    }
}
