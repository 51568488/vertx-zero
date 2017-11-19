package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.io.IO;
import io.vertx.zero.tool.mirror.Instance;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ZeroDynamic implements Node<JsonObject> {

    private static final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    private static final JsonObject ONE_DATA = new JsonObject();


    @Override
    public JsonObject read() {
        if (ONE_DATA.isEmpty()) {
            final ConcurrentMap<String, String> keys = node.read();
            final Set<String> skipped = Arrays
                    .stream(Plugins.DATA).collect(Collectors.toSet());
            for (final String key : keys.keySet()) {
                // Skip some internal keys.
                if (skipped.contains(key)) {
                    continue;
                }
                final String filename = keys.get(key);
                final JsonObject each = Fn.pool(Storage.CONFIG, filename,
                        () -> Fn.obtain(
                                new JsonObject(),
                                () -> IO.getYaml(filename),
                                filename));
                if (null != each) {
                    ONE_DATA.mergeIn(each, true);
                }
            }
        }
        return ONE_DATA;
    }
}
