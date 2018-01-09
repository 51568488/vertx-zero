package io.vertx.up.micro.ipc.client;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;

/**
 * Different implementation by type.
 */
public interface Spear {
    /**
     * Rpc Logical
     */
    Future<Envelop> send(
            final Vertx vertx,
            IpcData data);
}
