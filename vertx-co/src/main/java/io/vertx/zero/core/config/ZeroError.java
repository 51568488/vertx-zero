package io.vertx.zero.core.config;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import org.vie.fun.HPool;
import org.vie.util.Instance;

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
        final ZeroNode<JsonObject> node
                = HPool.exec(Storage.NODES, getKey(),
                () -> Instance.instance(ZeroPlugin.class, getKey()));
        // Read internal error to avoid conflicts.
        final String internalKey = "failure";
        final ZeroNode<JsonObject> internal
                = HPool.exec(Storage.NODES, internalKey,
                () -> Instance.instance(ZeroPlugin.class, internalKey));
        final JsonObject inneralData = internal.read();
        final JsonObject errorData = node.read();
        return inneralData.mergeIn(errorData);
    }
}
