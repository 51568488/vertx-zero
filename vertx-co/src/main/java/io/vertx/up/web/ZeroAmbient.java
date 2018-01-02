package io.vertx.up.web;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.log.internal.Log4JAnnal;
import io.vertx.zero.marshal.options.Opts;

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

    private static final Opts<JsonObject> OPTS = Opts.get();

    static {
        INJECTIONS = new ConcurrentHashMap<>();
        // 2. The injections must be configured in lime node.
        Fn.flingUp(() -> {
            final JsonObject opt = OPTS.ingest(KEY);
            Fn.itJObject(opt, (item, field) -> {
                final String plugin = item.toString();
                LOGGER.info(Info.PLUGIN_LOAD, KEY, field, plugin);
                INJECTIONS.put(field, Instance.clazz(plugin));
            });
        }, LOGGER);
    }

    public static Class<?> getPlugin(final String key) {
        return INJECTIONS.get(key);
    }

    public static ConcurrentMap<String, Class<?>> getInjections() {
        return INJECTIONS;
    }

    /**
     * Inited by ZeroGrid static
     */
    static void init() {
    }

    private ZeroAmbient() {
    }
}
