package io.vertx.core;

import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import org.vie.fun.HBool;
import org.vie.util.Instance;

class ClusterOptionsConverter {
    ClusterOptionsConverter() {
    }

    static void fromJson(final JsonObject json, final ClusterOptions obj) {
        if (json.getValue("enabled") instanceof Boolean) {
            obj.setEnabled(json.getBoolean("enabled"));
        }
        if (json.getValue("options") instanceof JsonObject) {
            obj.setOptions(json.getJsonObject("options"));
        }
        if (null != json.getValue("manager")) {
            final Class<?> clazz = Instance.clazz(json.getString("manager"));
            HBool.execTrue(null != clazz,
                    () -> {
                        // If null, keep default
                        final ClusterManager manager = Instance.instance(clazz);
                        obj.setManager(manager);
                        return null;
                    });
        }
    }
}
