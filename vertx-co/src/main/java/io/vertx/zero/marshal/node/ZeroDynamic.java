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

    private static final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    private static final JsonObject ONE_DATA = new JsonObject();

    private static JsonObject readConfig() {
        final ConcurrentMap<String, String> keys = node.read();
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
                ONE_DATA.mergeIn(each, true);
            }
        }
        return ONE_DATA;
    }

    static {
        final ConcurrentMap<String, String> keys = node.read();
        /**
         * Remote default
         */
        readConfig();
    }


    @Override
    public JsonObject read() {
        if (ONE_DATA.isEmpty()) {
            readConfig();
        }
        return ONE_DATA;
    }
}
