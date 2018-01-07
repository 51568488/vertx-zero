package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;

/**
 * Common income request
 */
public class JObjectImitate extends BaseImitate<JsonObject> {

    @Override
    public JsonObject request(
            final Message<Envelop> message) {
        return request(message, JsonObject.class);
    }

    @Override
    public JsonObject request(
            final Message<Envelop> message,
            final int index) {
        return request(message, index, JsonObject.class);
    }
}
