package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.zero.func.HFail;
import io.vertx.zero.func.HPool;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.io.IO;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentMap;

public class ZeroDynamic implements Node<JsonObject> {

    private transient final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Override
    public JsonObject read() {
        final JsonObject data = new JsonObject();
        final ConcurrentMap<String, String> keys = this.node.read();
        /**
         * Remote default
         */
        for (final String key : Plugins.DATA) {
            keys.remove(key);
        }
        for (final String key : keys.keySet()) {
            final String filename = keys.get(key);
            final JsonObject each = HPool.exec(Storage.CONFIG, filename,
                    () -> HFail.execDft(
                            () -> IO.getYaml(filename),
                            new JsonObject(), filename));
            if (null != each) {
                data.mergeIn(each, true);
            }
        }
        return data;
    }
}
