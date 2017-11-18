package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.func.HFail;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.io.IO;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class ZeroPlugin implements Node<JsonObject> {

    private transient final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    private transient final String key;

    public ZeroPlugin(final String key) {
        this.key = key;
    }

    @Override
    public JsonObject read() {
        // 1. Read all map data.
        final ConcurrentMap<String, String> dataMap = this.node.read();
        // 2. Read error configuration
        final String filename = dataMap.get(this.key);
        // 3. Read new data from extension file
        return HFail.execDft(
                () -> IO.getYaml(filename),
                new JsonObject(), filename);
    }
}
