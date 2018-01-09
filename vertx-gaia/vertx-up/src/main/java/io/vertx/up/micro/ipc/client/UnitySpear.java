package io.vertx.up.micro.ipc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.micro.ipc.DataEncap;
import io.vertx.up.plugin.rpc.RpcRepdor;
import io.vertx.up.plugin.rpc.RpcSslTool;

public class UnitySpear implements Spear {

    @Override
    public Future<Envelop> send(
            final Vertx vertx,
            final IpcData data) {
        // Channel
        final ManagedChannel channel = RpcSslTool.getChannel(vertx, data);
        final UnityServiceGrpc.UnityServiceVertxStub stub
                = UnityServiceGrpc.newVertxStub(channel);
        // Request
        final IpcRequest request = DataEncap.in(data);
        // Call and return to future
        final Future<Envelop> handler = Future.future();
        stub.unityCall(request, response ->
                // Reply
                RpcRepdor.create(getClass()).reply(handler, response));
        return handler;
    }
}
