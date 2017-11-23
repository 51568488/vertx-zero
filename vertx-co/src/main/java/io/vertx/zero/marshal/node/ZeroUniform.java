package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.zero.tool.io.IO;
import io.vertx.zero.tool.mirror.Instance;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ZeroDynamic implements Node<JsonObject> {

    private static final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);


    @Override
    public JsonObject read() {
        final JsonObject data = new JsonObject();
        if (data.isEmpty()) {
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
                        () -> Fn.getJvm(
                                new JsonObject(),
                                () -> IO.getYaml(filename),
                                filename));
                if (null != each) {
                    data.mergeIn(each, true);
                }
            }
        }
        return data;
    }
}
