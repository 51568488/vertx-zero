package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.zero.exception.ConfigKeyMissingException;
import io.vertx.zero.log.Annal;
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

    protected Annal getLogger() {
        return Annal.get(getClass());
    }

    public abstract String getKey();
}
