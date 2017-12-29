package io.vertx.zero.micro.config;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Plugins;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ensurer;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.eon.Info;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.ServerConfigException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.node.Node;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author lang
 * Http options for dynamic extension.
 */
public class DynamicVisitor extends HttpServerVisitor {

    private static final Annal LOGGER = Annal.get(DynamicVisitor.class);

    private transient final Node<JsonObject> NODE = Node.infix(Plugins.SERVER);

    private transient ServerType type;

    @Override
    public ConcurrentMap<Integer, HttpServerOptions> visit(final String... key)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 1, (Object[]) key);
        // 2. Visit the node for server
        final JsonObject data = this.NODE.read();

        Fn.flingZero(null == data || !data.containsKey(KEY), LOGGER,
                ServerConfigException.class,
                getClass(), null == data ? null : data.encode());
        // 3. Convert input parameters.
        this.type = ServerType.valueOf(key[Values.IDX]);
        return visit(data.getJsonArray(KEY));
    }

    private ConcurrentMap<Integer, HttpServerOptions> visit(final JsonArray serverData)
            throws ZeroException {
        getLogger().info(Info.INF_B_VERIFY, KEY, this.type, serverData.encode());
        Ruler.verify(KEY, serverData);
        final ConcurrentMap<Integer, HttpServerOptions> map =
                new ConcurrentHashMap<>();
        this.extract(serverData, map);
        getLogger().info(Info.INF_A_VERIFY, KEY, this.type, map.keySet());
        return map;
    }

    @Override
    protected boolean isServer(final JsonObject item) {
        return null != this.type &&
                this.type.match(item.getString(YKEY_TYPE));
    }
}
