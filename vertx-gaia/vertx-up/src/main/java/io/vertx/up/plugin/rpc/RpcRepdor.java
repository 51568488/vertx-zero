package io.vertx.up.plugin.rpc;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ipc.eon.IpcResponse;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception._500UnexpectedRpcException;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ipc.DataEncap;

public class RpcRepdor {

    private static final Annal LOGGER = Annal.get(RpcRepdor.class);

    private transient final Class<?> clazz;

    public static RpcRepdor create(final Class<?> clazz) {
        return new RpcRepdor(clazz);
    }

    private RpcRepdor(final Class<?> clazz) {
        this.clazz = clazz;
    }

    public void replyJson(
            final Future<JsonObject> handler,
            final AsyncResult<IpcResponse> response) {
        if (response.succeeded()) {
            final Envelop json = DataEncap.out(response.result());
            final JsonObject data = json.data();
            LOGGER.info(Info.CLIENT_RESPONSE, data);
            handler.complete(data);
        } else {
            final Throwable ex = response.cause();
            if (null != ex) {
                final Envelop envelop =
                        Envelop.failure(new _500UnexpectedRpcException(this.clazz, ex));
                handler.complete(new JsonObject(envelop.response()));
            }
        }
    }

    public void reply(
            final Future<Envelop> handler,
            final AsyncResult<IpcResponse> response
    ) {
        if (response.succeeded()) {
            handler.complete(DataEncap.out(response.result()));
        } else {
            final Throwable ex = response.cause();
            if (null != ex) {
                final Envelop envelop =
                        Envelop.failure(new _500UnexpectedRpcException(this.clazz, ex));
                handler.complete(envelop);
            }
        }
    }
}
