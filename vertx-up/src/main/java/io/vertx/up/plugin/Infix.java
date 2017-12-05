package io.vertx.up.plugin;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.ConfigKeyMissingException;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

import java.util.function.Function;

public interface Infix {

    <T> T get();

    static <R> R init(final String key,
                      final Function<JsonObject, R> executor,
                      final Class<?> clazz) {
        final Node<JsonObject> node = Instance.instance(ZeroUniform.class);
        final JsonObject options = node.read();
        final Annal logger = Annal.get(clazz);
        Fn.flingUp(null == options || !options.containsKey(key)
                , logger, ConfigKeyMissingException.class,
                clazz, key);
        return executor.apply(options.getJsonObject(key));
    }
}
