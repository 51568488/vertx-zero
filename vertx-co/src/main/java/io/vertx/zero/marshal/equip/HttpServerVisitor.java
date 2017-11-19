package io.vertx.zero.marshal.equip;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.exception.ServerConfigException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.func.HBool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.marshal.Transformer;
import io.vertx.zero.marshal.node.JObjectBase;
import io.vertx.zero.marshal.node.ZeroServer;
import io.vertx.zero.tool.Ensurer;
import io.vertx.zero.tool.mirror.Instance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 */
public class HttpServerVisitor implements ServerVisitor<HttpServerOptions> {

    private static final Annal LOGGER = Annal.get(HttpServerVisitor.class);

    private transient final JObjectBase NODE
            = Instance.singleton(ZeroServer.class);
    private transient final Transformer<HttpServerOptions>
            transformer = Instance.singleton(HttpServerStrada.class);

    /**
     * @return
     * @throws ZeroException
     */
    @Override
    public ConcurrentMap<Integer, HttpServerOptions> visit(final String... key)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, (Object[]) key);
        // 2. Visit the node for server, http
        final JsonObject data = this.NODE.read();
        return HBool.execZero(null == data || !data.containsKey(Key.SERVER),
                () -> {
                    throw new ServerConfigException(getClass(), null == data ? null : data.encode());
                }, () -> {
                    // 3. Convert server data to Map
                    return visit(data.getJsonArray(Key.SERVER));
                });
    }

    private ConcurrentMap<Integer, HttpServerOptions> visit(final JsonArray serverData)
            throws ZeroException {
        LOGGER.info(Info.INF_B_VERIFY, Key.SERVER, serverData.encode());
        Ruler.verify(Files.SERVER, serverData);
        final ConcurrentMap<Integer, HttpServerOptions> map =
                new ConcurrentHashMap<>();
        Fn.itJArray(serverData, JsonObject.class, (item, index) -> {
            if (ServerType.HTTP.match(item.getString(YKEY_TYPE))) {
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
        return map;
    }

    private static int extractPort(final JsonObject config) {
        if (null != config) {
            return config.getInteger("port", HttpServerOptions.DEFAULT_PORT);
        }
        return HttpServerOptions.DEFAULT_PORT;
    }
}
