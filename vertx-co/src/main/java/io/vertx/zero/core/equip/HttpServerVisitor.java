package io.vertx.zero.core.equip;

import com.vie.cv.em.ServerType;
import com.vie.exception.ZeroException;
import com.vie.exception.ke.ServerConfigException;
import com.vie.fun.HBool;
import com.vie.fun.HJson;
import com.vie.fun.HNull;
import com.vie.util.Ensurer;
import com.vie.util.Instance;
import com.vie.util.log.Annal;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ce.Ruler;
import io.vertx.zero.core.Transformer;
import io.vertx.zero.core.config.JObjectBase;
import io.vertx.zero.core.config.ZeroServer;

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
        Ensurer.eqLength(getClass(), 0, key);
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
        LOGGER.info(Message.INF_B_VERIFY, Key.SERVER, serverData.encode());
        Ruler.verify(Files.SERVER, serverData);
        final ConcurrentMap<Integer, HttpServerOptions> map =
                new ConcurrentHashMap<>();
        HJson.execIt(serverData, (item, index) -> {
            if (ServerType.HTTP.match(item.getString(YKEY_TYPE))) {
                // 1. Extract port
                final int port = extractPort(item.getJsonObject(YKEY_CONFIG));
                // 2. Convert JsonObject to HttpServerOptions
                final HttpServerOptions options = this.transformer.transform(item);
                HNull.exec(() -> {
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
