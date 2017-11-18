package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.zero.func.HFail;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.io.IO;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentMap;

public class ZeroServer extends JObjectBase {

    private transient final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Override
    public String getKey() {
        return Plugins.SERVER;
    }

    @Override
    public JsonObject read() {
        final ConcurrentMap<String, String> keys = this.node.read();

        final String filename = keys.get(getKey());

        return HFail.execDft(
                () -> IO.getYaml(filename),
                new JsonObject(), filename);
    }
}
