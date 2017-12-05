package io.vertx.core;

import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.mirror.Instance;

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
        final Object managerObj = json.getValue("manager");
        Fn.safeNull(() -> {
            final Class<?> clazz = Instance.clazz(managerObj.toString());
            Fn.safeNull(() -> {
                // If null, keep default
                final ClusterManager manager = Instance.instance(clazz);
                obj.setManager(manager);
            }, clazz);
        }, managerObj);
    }
}
