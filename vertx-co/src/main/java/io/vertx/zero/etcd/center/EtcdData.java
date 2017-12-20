package io.vertx.zero.etcd.center;

import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

public class EtcdData {
    /**
     * Config data
     */
    private static final String KEY = "etcd";
    private final transient JsonObject config = new JsonObject();
    private final Node<JsonObject> node = Instance.singleton(ZeroUniform.class);

    public static EtcdData create() {
        return new EtcdData();
    }

    private EtcdData() {
        final JsonObject config = this.node.read();
        if (config.containsKey(KEY)) {
            this.config.mergeIn(config.getJsonObject(KEY));
        }
    }
}
