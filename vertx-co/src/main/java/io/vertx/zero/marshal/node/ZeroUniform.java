package io.vertx.zero.marshal.node;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ZeroUniform implements Node<JsonObject> {

    private static final Annal LOGGER = Annal.get(ZeroUniform.class);

    private static final Node<ConcurrentMap<String, String>> node
            = Instance.singleton(ZeroLime.class);

    @Override
    public JsonObject read() {
        final JsonObject data = new JsonObject();
        final ConcurrentMap<String, String> keys = node.read();
        final Set<String> skipped = Arrays
                .stream(Plugins.DATA).collect(Collectors.toSet());
        LOGGER.info(Info.UNIFORM, keys.keySet(), skipped);
        // RxJava2 version
        Observable.fromIterable(keys.keySet())
                .filter(item -> !skipped.contains(item))
                .map(key -> ZeroTool.read(key, true))
                .filter(Objects::nonNull)
                .subscribe(item -> {
                    System.out.println(item);
                    // data.mergeIn(item, true);
                });
        System.out.println(data);
        return data;
    }
}
