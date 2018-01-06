package io.vertx.up.micro.ipc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.exception._500UnexpectedRpcException;
import io.vertx.up.micro.ipc.DataEncap;

public class UnityStub implements RpcStub {

    @Override
    public Future<Envelop> send(
            final Vertx vertx,
            final IpcData data) {
        // Channel
        final ManagedChannel channel = SslTool.getChannel(vertx, data);
        final UnityServiceGrpc.UnityServiceVertxStub stub
                = UnityServiceGrpc.newVertxStub(channel);
        // Request
        final IpcRequest request = DataEncap.in(data);
        // Call and return to future
        final Future<Envelop> handler = Future.future();
        stub.unityCall(request, response -> {
            if (response.succeeded()) {
                handler.complete(DataEncap.out(response.result()));
            } else {
                final Throwable ex = response.cause();
                if (null != ex) {
                    final Envelop envelop = Envelop.failure(
                            new _500UnexpectedRpcException(getClass(), ex)
                    );
                    handler.complete(envelop);
                }
            }
        });
        return handler;
    }
}
