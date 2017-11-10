package io.vertx.zero.core.equip;

import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Transformer;
import org.vie.fun.HBool;

public class VertxStrada implements Transformer<VertxOptions> {

    @Override
    public VertxOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(UprightVisitor.YKEY_OPTIONS, null);
        return HBool.exec(null == config,
                VertxOptions::new,
                () -> new VertxOptions(config));
    }
}
