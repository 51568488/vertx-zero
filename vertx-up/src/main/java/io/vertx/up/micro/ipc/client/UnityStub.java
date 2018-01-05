package io.vertx.up.micro.ipc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Vertx;
import io.vertx.grpc.VertxChannelBuilder;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.micro.ipc.DataEncap;

public class UnityStub implements RpcStub {

    @Override
    public void send(final Vertx vertx, final IpcData data) {
        final String grpcHost = data.getHost();
        final Integer grpcPort = data.getPort();
        // Channel
        final ManagedChannel channel = VertxChannelBuilder
                .forAddress(vertx, grpcHost, grpcPort)
                .build();
        final UnityServiceGrpc.UnityServiceVertxStub stub
                = UnityServiceGrpc.newVertxStub(channel);
        // Request
        final IpcRequest request = DataEncap.in(data);
        // Call and return to future
        stub.unityCall(request, response -> {
            if (response.succeeded()) {
                System.out.println(response.result().getEnvelop().getBody());
            }
        });
    }
}
