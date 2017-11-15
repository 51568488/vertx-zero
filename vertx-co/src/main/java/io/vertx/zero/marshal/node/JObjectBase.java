package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.func.HPool;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.mirror.Instance;

public abstract class JObjectBase implements Node<JsonObject> {
    @Override
    public JsonObject read() {
        final Node<JsonObject> node
                = HPool.exec(Storage.NODES, getKey(),
                () -> Instance.instance(ZeroPlugin.class, getKey()));
        return node.read();
    }

    public abstract String getKey();
}
