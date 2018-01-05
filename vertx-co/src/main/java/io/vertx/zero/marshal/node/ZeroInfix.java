package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

public class ZeroInfix implements Node<JsonObject> {

    private transient final String key;

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
