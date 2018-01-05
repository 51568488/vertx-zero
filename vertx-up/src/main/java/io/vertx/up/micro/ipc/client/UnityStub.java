package io.vertx.up.micro.ipc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Vertx;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.micro.ipc.DataEncap;

public class UnityStub implements RpcStub {

    @Override
    public void send(final Vertx vertx, final IpcData data) {
        // Channel
        final ManagedChannel channel = SslTool.getChannel(vertx, data);
        final UnityServiceGrpc.UnityServiceVertxStub stub
                = UnityServiceGrpc.newVertxStub(channel);
        // Request
        final IpcRequest request = DataEncap.in(data);
        // Call and return to future
        stub.unityCall(request, response -> {
            if (response.succeeded()) {
                System.out.println(response.result().getEnvelop().getBody());
            } else {
                final Throwable ex = response.cause();
                if (null != ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
