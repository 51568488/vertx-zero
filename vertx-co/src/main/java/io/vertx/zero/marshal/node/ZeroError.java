package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;

/**
 * @author lang
 */
public class ZeroError extends JObjectBase {

    @Override
    public String getKey() {
        return "error";
    }

    @Override
    public JsonObject read() {
        return mergeIn("failure");
    }
}
