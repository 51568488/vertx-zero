package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;

public class ZeroResolver extends JObjectBase {
    @Override
    public String getKey() {
        return "resolver";
    }

    @Override
    public JsonObject read() {
        return mergeIn("resolver-internal");
    }
}
