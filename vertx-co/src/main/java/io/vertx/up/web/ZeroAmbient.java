package io.vertx.up.web;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.eon.em.YamlType;
import io.vertx.zero.func.HJson;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.log.internal.Log4JAnnal;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.marshal.node.ZeroLime;
import io.vertx.zero.marshal.options.Opts;
import io.vertx.zero.tool.io.IO;
import io.vertx.zero.tool.mirror.Instance;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Critical Environment
 *
 * @author lang
 */
public final class ZeroAmbient {

    private static final String KEY = "inject";
    /**
     * Avoid dead lock, use internal Log only.
     **/
    private static final Annal LOGGER = new Log4JAnnal(ZeroAmbient.class);

    private static final ConcurrentMap<String, Class<?>> INJECTIONS;

    private static final ConcurrentMap<String, YamlType> GRIDS =
            new ConcurrentHashMap<>();

    private static final ConcurrentMap<String, JsonObject> OBJECTS =
            new ConcurrentHashMap<>();

    private static final ConcurrentMap<String, JsonArray> ARRAYS =
            new ConcurrentHashMap<>();

    private static final Opts<JsonObject> OPTS = Opts.get();

    static {
        INJECTIONS = new ConcurrentHashMap<>();
        // 1. Pre-check if lime configured in vertx.yml
        final Node<ConcurrentMap<String, String>> vertxNode =
                Instance.singleton(ZeroLime.class);
        final ConcurrentMap<String, String> limeData = vertxNode.read();
        // 2. The injections must be configured in lime node.
        HTry.exec(() -> {
            final JsonObject opt = OPTS.ingest(KEY);
            HJson.execIt(opt, (item, field) -> {
                if (limeData.containsKey(field)) {
                    INJECTIONS.put(field, Instance.clazz(item.toString()));
                }
            });
        }, LOGGER);
    }

    public static Class<?> getPlugin(final String key) {
        return INJECTIONS.get(key);
    }

    public static Set<String> getPluginNames() {
        return INJECTIONS.keySet();
    }

    /**
     * Inited by ZeroGrid static
     */
    static void init() {
        final Node<ConcurrentMap<String, String>> node
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

    private ZeroAmbient() {
    }
}
