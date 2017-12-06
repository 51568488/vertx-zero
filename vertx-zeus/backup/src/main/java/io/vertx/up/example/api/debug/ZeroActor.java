package io.vertx.up.example.api.debug;

import io.vertx.core.json.JsonObject;

public class ZeroActor implements ZeroApi {

    @Override
    public JsonObject send(final JsonObject body) {
        return body;
    }
}
