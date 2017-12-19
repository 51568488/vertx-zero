package io.vertx.zero.marshal.node;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.io.IO;
import io.vertx.up.tool.mirror.Instance;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ZeroUniform implements Node<JsonObject> {

    private static final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Override
    public JsonObject read() {
        final JsonObject data = new JsonObject();
        final ConcurrentMap<String, String> keys = node.read();
        final Set<String> skipped = Arrays
                .stream(Plugins.DATA).collect(Collectors.toSet());
        // RxJava2 version
        Observable.fromIterable(keys.keySet())
                .filter(item -> !skipped.contains(item))
                .map(key -> Fn.pool(Storage.CONFIG, keys.get(key),
                        () -> Fn.getJvm(new JsonObject(),
                                () -> IO.getYaml(keys.get(key)),
                                keys.get(key))))
                .filter(Objects::nonNull)
                .subscribe(item -> data.mergeIn(item, true));
        return data;
    }
}
