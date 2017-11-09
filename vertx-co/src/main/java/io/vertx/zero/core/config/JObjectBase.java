package io.vertx.zero.core.config;

import com.vie.fun.HPool;
import com.vie.util.Instance;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;

public abstract class JObjectBase implements ZeroNode<JsonObject> {
    @Override
    public JsonObject read() {
        final ZeroNode<JsonObject> node
                = HPool.exec(Storage.NODES, getKey(),
                () -> Instance.instance(ZeroPlugin.class, getKey()));
        return node.read();
    }

    public abstract String getKey();
}
