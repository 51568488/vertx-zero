package io.vertx.up.micro.ipc;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.servicediscovery.Record;
import io.vertx.tp.ipc.eon.IpcEnvelop;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.eon.em.Format;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;

/**
 * Data serialization to set data
 * 1. Client data Envelop -> RpcEnvelop
 * 2. Server data RpcEnvelop -> Method arguments
 * 3. Method return value -> RpcEnvelop
 * 4. RpcEnvelop extract -> Envelop
 */
public class DataEncap {

    public static void in(final IpcData data, final Record record) {
        if (null != record) {
            data.setHost(record.getLocation().getString("host"));
            data.setPort(record.getLocation().getInteger("port"));
        }
    }

    public static void in(final IpcData data, final Envelop envelop) {
        if (null != envelop) {
            // User
            final User user = envelop.user();
            final JsonObject sendData = new JsonObject();
            if (null != user) {
                sendData.put("user", user.principal());
            }
            // Header
            final MultiMap headers = envelop.headers();
            final JsonObject headerData = new JsonObject();
            headers.forEach((entry) -> headerData.put(entry.getKey(), entry.getValue()));
            sendData.put("header", headerData);
            // Data
            final JsonObject businessData = envelop.data();
            sendData.put("data", businessData);
            data.setData(sendData.toBuffer());
        }
    }

    public static IpcRequest in(final IpcData data) {
        final IpcEnvelop envelop = IpcEnvelop.newBuilder()
                .setBody(data.getData().toString())
                .setType(Format.JSON).build();
        return IpcRequest.newBuilder()
                .setEnvelop(envelop)
                .build();
    }
}
