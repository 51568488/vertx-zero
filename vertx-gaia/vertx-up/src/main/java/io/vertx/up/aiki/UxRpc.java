package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.up.plugin.rpc.RpcClient;
import io.vertx.up.plugin.rpc.RpcInfix;

class UxRpc {

    private static final RpcClient CLIENT = RpcInfix.getClient();

    private static final Annal LOGGER = Annal.get(UxRpc.class);

    public static Future<JsonObject> thenRpc(final String name,
                                             final String address,
                                             final JsonObject params) {
        final Future<JsonObject> result = Future.future();
        CLIENT.connect(name, address, params, handler -> {
            LOGGER.info(Info.RPC_RESULT, name, address, params, handler.result());
            result.complete(handler.result());
        });
        return result;
    }
}
