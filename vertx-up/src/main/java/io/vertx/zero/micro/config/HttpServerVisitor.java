package io.vertx.zero.micro.config;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ensurer;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.config.ServerVisitor;
import io.vertx.zero.eon.Info;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.ServerConfigException;
import io.vertx.zero.marshal.Transformer;
import io.vertx.zero.marshal.node.Node;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 * Http options only, it's standard
 */
public class HttpServerVisitor implements ServerVisitor<HttpServerOptions> {

    private transient final Node<JsonObject> NODE = Node.infix(Plugins.SERVER);
    protected transient final Transformer<HttpServerOptions>
            transformer = Instance.singleton(HttpServerStrada.class);

    /**
     * @return Server config to generate HttpServerOptions by port
     * @throws ZeroException ServerConfigException
     */
    @Override
    public ConcurrentMap<Integer, HttpServerOptions> visit(final String... key)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, (Object[]) key);
        // 2. Visit the node for server, http
        final JsonObject data = this.NODE.read();

        Fn.flingZero(null == data || !data.containsKey(KEY), getLogger(),
                ServerConfigException.class,
                getClass(), null == data ? null : data.encode());

        return visit(data.getJsonArray(KEY));
    }

    private ConcurrentMap<Integer, HttpServerOptions> visit(final JsonArray serverData)
            throws ZeroException {
        getLogger().info(Info.INF_B_VERIFY, KEY, getType(), serverData.encode());
        Ruler.verify(KEY, serverData);
        final ConcurrentMap<Integer, HttpServerOptions> map =
                new ConcurrentHashMap<>();
        this.extract(serverData, map);
        getLogger().info(Info.INF_A_VERIFY, KEY, getType(), map.keySet());
        return map;
    }

    protected void extract(final JsonArray serverData, final ConcurrentMap<Integer, HttpServerOptions> map) {
        Fn.itJArray(serverData, JsonObject.class, (item, index) -> {
            if (isServer(item)) {
                // 1. Extract port
                final int port = extractPort(item.getJsonObject(YKEY_CONFIG));
                // 2. Convert JsonObject to HttpServerOptions
                final HttpServerOptions options = this.transformer.transform(item);
                Fn.safeNull(() -> {
                    // 3. Add to map;
                    map.put(port, options);
                }, port, options);
            }
        });
    }

    protected boolean isServer(final JsonObject item) {
        return getType().match(item.getString(YKEY_TYPE));
    }

    protected int extractPort(final JsonObject config) {
        if (null != config) {
            return config.getInteger("port", HttpServerOptions.DEFAULT_PORT);
        }
        return HttpServerOptions.DEFAULT_PORT;
    }

    protected ServerType getType() {
        return ServerType.HTTP;
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
