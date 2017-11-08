package io.vertx.zero.ke.equip;

import com.vie.hoc.HBool;
import io.vertx.core.ClusterOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.Transformer;

public class ClusterStrada implements Transformer<ClusterOptions> {

    @Override
    public ClusterOptions transform(final JsonObject config) {
        return HBool.exec(null == config,
                ClusterOptions::new,
                () -> new ClusterOptions(config));
    }
}
