package io.vertx.up.plugin.rpc.client;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.flux.IpcData;

public interface RpcStub {
    /**
     * Connect to remote by channel
     */
    Future<JsonObject> traffic(final IpcData data);
}
