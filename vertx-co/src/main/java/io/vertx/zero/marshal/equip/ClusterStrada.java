package io.vertx.zero.marshal.equip;

import io.vertx.core.ClusterOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.func.HBool;
import io.vertx.zero.marshal.Transformer;

public class ClusterStrada implements Transformer<ClusterOptions> {

    @Override
    public ClusterOptions transform(final JsonObject config) {
        return HBool.exec(null == config,
                ClusterOptions::new,
                () -> new ClusterOptions(config));
    }
}
