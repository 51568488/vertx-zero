package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.exception.ConfigKeyMissingException;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.tool.mirror.Instance;

public abstract class JObjectBase implements Node<JsonObject> {
    @Override
    public JsonObject read() {
        final Node<JsonObject> dyanmic = Instance.singleton(ZeroDynamic.class);
        final JsonObject config = dyanmic.read();

        Fn.flingUp(!config.containsKey(getKey()), getLogger(),
                ConfigKeyMissingException.class, getClass(), getKey());

        return config.getJsonObject(this.getKey());
    }

    protected JsonObject mergeIn(final String addtional) {
        final JsonObject data = new JsonObject();
        final Node<JsonObject> node = Instance.instance(ZeroPlugin.class, getKey());
        final JsonObject source = node.read();
        final Node<JsonObject> internal = Instance.instance(ZeroPlugin.class, addtional);
        final JsonObject target = internal.read();
        if (null != target) {
            data.mergeIn(target, true);
        }
        if (null != source) {
            data.mergeIn(source, true);
        }
        return data;
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }

    public abstract String getKey();
}
