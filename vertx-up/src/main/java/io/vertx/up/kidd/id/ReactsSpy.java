package io.vertx.up.kidd.id;

import io.vertx.core.json.JsonObject;
import io.vertx.up.kidd.Spy;
import io.vertx.up.tool.mirror.Instance;

import java.util.List;

public class ReactsSpy implements Spy<List<JsonObject>> {

    private transient final Spy<JsonObject> spy = Instance.singleton(ReactSpy.class);

    @Override
    public List<JsonObject> in(final List<JsonObject> request) {
        for (final JsonObject item : request) {
            this.spy.in(item);
        }
        return request;
    }

    @Override
    public List<JsonObject> out(final List<JsonObject> response) {
        for (final JsonObject item : response) {
            this.spy.out(item);
        }
        return response;
    }
}
