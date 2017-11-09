package io.vertx.zero.core.config;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import org.vie.fun.HFail;
import org.vie.util.Instance;
import org.vie.util.io.IO;

import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class ZeroPlugin implements ZeroNode<JsonObject> {

    private transient final ZeroNode<ConcurrentMap<String, String>> node
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
