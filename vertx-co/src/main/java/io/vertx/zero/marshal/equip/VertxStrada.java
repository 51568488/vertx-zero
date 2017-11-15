package io.vertx.zero.marshal.equip;

import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.func.HBool;
import io.vertx.zero.marshal.Transformer;

public class VertxStrada implements Transformer<VertxOptions> {

    @Override
    public VertxOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(NodeVisitor.YKEY_OPTIONS, null);
        return HBool.exec(null == config,
                VertxOptions::new,
                () -> new VertxOptions(config));
    }
}
