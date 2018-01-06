package io.vertx.up.micro.ipc.client;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;

public class DupliexStub implements RpcStub {

    @Override
    public Future<Envelop> send(final Vertx vertx, final IpcData data) {
        // TODO: Dupliex Mode
        return null;
    }
}
