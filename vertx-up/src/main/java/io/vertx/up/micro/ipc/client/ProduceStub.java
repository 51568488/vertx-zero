package io.vertx.up.micro.ipc.client;

import io.vertx.core.Vertx;
import io.vertx.up.atom.flux.IpcData;

public class ProduceStub implements RpcStub {

    @Override
    public void send(final Vertx vertx, final IpcData data) {
        // TODO: Produce Mode
    }
}
