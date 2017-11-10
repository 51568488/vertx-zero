package io.vertx.zero.core.equip;

import io.vertx.core.ClusterOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Transformer;
import org.vie.fun.HBool;

public class ClusterStrada implements Transformer<ClusterOptions> {

    @Override
    public ClusterOptions transform(final JsonObject config) {
        return HBool.exec(null == config,
                ClusterOptions::new,
                () -> new ClusterOptions(config));
    }
}
