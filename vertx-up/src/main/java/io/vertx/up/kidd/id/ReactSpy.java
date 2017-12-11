package io.vertx.up.kidd.id;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Spy;

public class ReactSpy implements Spy<JsonObject> {

    @Override
    public JsonObject in(final JsonObject request) {
        return process(request, "key", "_id");
    }

    @Override
    public JsonObject out(final JsonObject response) {
        return process(response, "_id", "key");
    }

    private JsonObject process(final JsonObject source,
                               final String fromKey,
                               final String toKey) {
        Fn.safeNull(() -> {
            if (source.containsKey(fromKey)) {
                source.put(toKey, source.getValue(fromKey));
                source.remove(fromKey);
            }
        }, source);
        return source;
    }
}
