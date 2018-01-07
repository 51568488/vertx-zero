package io.vertx.zero.micro.config;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ensurer;
import io.vertx.zero.config.ServerVisitor;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.ServerConfigException;
import io.vertx.zero.marshal.node.Node;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Micro service mode only, Fix for http endpoint part
 */
public class NamesVisitor implements ServerVisitor<String> {

    private transient final Node<JsonObject> NODE = Node.infix(Plugins.SERVER);
    private transient ServerType type = null;

    @Override
    public ConcurrentMap<Integer, String> visit(final String... key)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 1, (Object[]) key);
        // 2. Visit the node for server, http
        final JsonObject data = this.NODE.read();

        Fn.flingZero(null == data || !data.containsKey(KEY), getLogger(),
                ServerConfigException.class,
                getClass(), null == data ? null : data.encode());
        // 3. Extract names.
        final JsonArray raw = data.getJsonArray(KEY);
        this.type = ServerType.valueOf(key[Values.IDX]);
        return extract(raw);
    }

    private ConcurrentMap<Integer, String> extract(final JsonArray serverData) {
        final ConcurrentMap<Integer, String> map = new ConcurrentHashMap<>();
        Fn.itJArray(serverData, JsonObject.class, (item, index) -> {
            if (isServer(item)) {
                // 1. Extract port
                final int port = extractPort(item.getJsonObject(YKEY_CONFIG));
                Fn.safeNull(() -> {
                    // 3. Add to map;
                    map.put(port, item.getString(YKEY_NAME));
                }, port);
            }
        });
        return map;
    }

    private boolean isServer(final JsonObject item) {
        return null != this.type &&
                this.type.match(item.getString(YKEY_TYPE));
    }

    private int extractPort(final JsonObject config) {
        if (null != config) {
            return config.getInteger("port", HttpServerOptions.DEFAULT_PORT);
        }
        return HttpServerOptions.DEFAULT_PORT;
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
