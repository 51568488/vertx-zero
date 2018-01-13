package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.up.atom.Envelop;

@Deprecated
public class JArrayImitate extends BaseImitate<JsonArray> {

    @Override
    public JsonArray request(
            final Message<Envelop> message
    ) {
        return this.request(message, JsonArray.class);
    }

    @Override
    public JsonArray request(
            final Message<Envelop> message,
            final int index
    ) {
        return this.request(message, index, JsonArray.class);
    }
}
