package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ZeroInfix implements Node<JsonObject> {

    private transient final String key;

    static final ConcurrentMap<String, Node<JsonObject>> REFERENCES
            = new ConcurrentHashMap<>();

    ZeroInfix(final String key) {
        this.key = key;
    }

    @Override
    public JsonObject read() {
        // Not null because execNil
        final JsonObject config = ZeroTool.read(this.key, true);
        return Fn.getJvm(new JsonObject(), () -> config, config);
    }
}
