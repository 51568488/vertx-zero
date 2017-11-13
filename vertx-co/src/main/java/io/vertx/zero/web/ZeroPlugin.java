package io.vertx.zero.web;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import io.vertx.zero.core.config.ZeroLime;
import io.vertx.zero.cv.Plugins;
import org.vie.cv.em.YamlType;
import org.vie.util.Instance;
import org.vie.util.io.IO;
import org.vie.util.log.Annal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Only access by ZeroGird
 */
class ZeroPlugin {

    private static final Annal LOGGER = Annal.get(ZeroPlugin.class);

    private static final ConcurrentMap<String, YamlType> GRIDS =
            new ConcurrentHashMap<>();

    private static final ConcurrentMap<String, JsonObject> OBJECTS =
            new ConcurrentHashMap<>();

    private static final ConcurrentMap<String, JsonArray> ARRAYS =
            new ConcurrentHashMap<>();

    /**
     * Inited by ZeroGrid static
     */
    static void init() {
        final ZeroNode<ConcurrentMap<String, String>> node
                = Instance.singleton(ZeroLime.class);
        final ConcurrentMap<String, String> metadata = node.read();
        for (final String item : Plugins.DATA) {
            metadata.remove(item);
        }
        // Loaded resource options into
        for (final String name : metadata.keySet()) {
            final String filename = metadata.get(name);
            final YamlType type = IO.getYamlType(filename);
            if (YamlType.OBJECT == type) {
                final JsonObject data = IO.getYaml(filename);
                LOGGER.info(Info.PLUGIN_LOAD, name, filename, type, data.encode());
                OBJECTS.put(name, data);
            } else {
                final JsonArray data = IO.getYaml(filename);
                LOGGER.info(Info.PLUGIN_LOAD, name, filename, type, data.encode());
                ARRAYS.put(name, data);
            }
            // Fill types.
            GRIDS.put(name, type);
        }
    }

    static YamlType getType(final String name) {
        return GRIDS.get(name);
    }

    static JsonObject getObject(final String name) {
        return OBJECTS.get(name);
    }

    static JsonArray getArray(final String name) {
        return ARRAYS.get(name);
    }
}
