package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.mirror.Instance;

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
        final Node<JsonObject> node = Instance.instance(ZeroPlugin.class, getKey());
        // Read internal error to avoid conflicts.
        final String internalKey = "failure";
        final Node<JsonObject> internal = Instance.instance(ZeroPlugin.class, internalKey);
        final JsonObject inneralData = internal.read();
        final JsonObject errorData = node.read();
        return inneralData.mergeIn(errorData);
    }
}
