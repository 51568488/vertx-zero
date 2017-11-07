package io.vertx.zero.ke.equip;

import com.vie.hoc.HBool;
import com.vie.hoc.HJson;
import com.vie.hoc.HNull;
import com.vie.hors.ZeroException;
import com.vie.log.Annal;
import com.vie.util.Ensurer;
import com.vie.util.Instance;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ce.Ruler;
import io.vertx.zero.em.ServerType;
import io.vertx.zero.hors.ke.ServerConfigException;
import io.vertx.zero.ke.Transformer;
import io.vertx.zero.ke.config.JObjectBase;
import io.vertx.zero.ke.config.ZeroServer;

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
            TRANS = Instance.singleton(HttpServerStrada.class);

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
        return HBool.execZero(null == data || !data.containsKey("server"),
                () -> {
                    throw new ServerConfigException(getClass(), null == data ? null : data.encode());
                }, () -> {
                    // 3. Convert server data to Map
                    return visit(data.getJsonArray("server"));
                });
    }

    private ConcurrentMap<Integer, HttpServerOptions> visit(final JsonArray serverData)
            throws ZeroException {
        LOGGER.info(Message.INF_B_VERIFY, "server", serverData.encode());
        Ruler.verify(Files.SERVER, serverData);
        final ConcurrentMap<Integer, HttpServerOptions> map =
                new ConcurrentHashMap<>();
        HJson.execIt(serverData, (item, index) -> {
            if (ServerType.HTTP.match(item.getString("type"))) {
                // 1. Extract port
                final int port = extractPort(item.getJsonObject("config"));
                // 2. Convert JsonObject to HttpServerOptions
                final HttpServerOptions options = this.TRANS.transform(item);
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
