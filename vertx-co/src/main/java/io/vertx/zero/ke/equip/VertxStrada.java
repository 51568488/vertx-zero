package io.vertx.zero.ke.equip;

import com.vie.hoc.HBool;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.Transformer;

public class VertxStrada implements Transformer<VertxOptions> {

    @Override
    public VertxOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(UprightVisitor.YKEY_OPTIONS, null);
        return HBool.exec(null == config,
                VertxOptions::new,
                () -> new VertxOptions(config));
    }
}
