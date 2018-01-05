package io.vertx.up.micro.ipc;

import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.servicediscovery.Record;
import io.vertx.tp.ipc.eon.IpcEnvelop;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.eon.em.Format;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.atom.hold.VirtualUser;
import io.vertx.up.eon.em.IpcType;

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
            data.setName(record.getName());
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
            sendData.put("config", data.getConfig());
            // Data Prepared finished.
            sendData.put("address", data.getAddress());
            data.setData(sendData.toBuffer());
        }
    }

    /**
     * @param data
     * @return
     */
    public static IpcRequest in(final IpcData data) {
        final IpcEnvelop envelop = IpcEnvelop.newBuilder()
                .setBody(data.getData().toString())
                .setType(Format.JSON).build();
        return IpcRequest.newBuilder()
                .setEnvelop(envelop)
                .build();
    }

    /**
     * Middle process
     *
     * @param request
     * @param type
     */
    public static IpcData consume(final IpcRequest request, final IpcType type) {
        final IpcData ipcData = new IpcData();
        final IpcEnvelop envelop = request.getEnvelop();
        final String data = envelop.getBody();
        final JsonObject json = new JsonObject(data);
        // Address convert
        if (json.containsKey("address")) {
            ipcData.setAddress(json.getString("address"));
            json.remove("address");
        }
        ipcData.setData(Buffer.buffer(data));
        ipcData.setType(type);
        return ipcData;
    }

    /**
     * Final hitted
     *
     * @param data
     * @return
     */
    public static Envelop consume(final IpcData data) {
        final JsonObject json = data.getData().toJsonObject();
        Envelop envelop = Envelop.ok();
        // 1. Headers
        if (null != json) {
            // 2.Rebuild
            if (json.containsKey("data")) {
                envelop = Envelop.success(json.getValue("data"));
            }
            // 3.Header
            if (null != json.getValue("header")) {
                final MultiMap headers = MultiMap.caseInsensitiveMultiMap();
                final JsonObject headerData = json.getJsonObject("header");
                for (final String key : headerData.fieldNames()) {
                    final Object value = headerData.getValue(key);
                    if (null != value) {
                        headers.set(key, value.toString());
                    }
                }
                envelop.setHeaders(headers);
            }
            // 4.User
            if (null != json.getValue("user")) {
                envelop.setUser(new VirtualUser(json.getJsonObject("user")));
            }
        }
        return envelop;
    }
}
