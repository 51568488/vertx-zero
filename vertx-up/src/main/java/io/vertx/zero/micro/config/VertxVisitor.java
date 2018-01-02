package io.vertx.zero.micro.config;

import io.vertx.core.ClusterOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Ensurer;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.config.NodeVisitor;
import io.vertx.zero.eon.Info;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.ClusterConflictException;
import io.vertx.zero.marshal.Transformer;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroVertx;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VertxVisitor implements NodeVisitor {
    private static final Annal LOGGER = Annal.get(VertxVisitor.class);

    private static final String KEY = "vertx";

    private transient final Node<JsonObject> NODE
            = Instance.singleton(ZeroVertx.class);
    private transient final Transformer<VertxOptions>
            transformer = Instance.singleton(VertxStrada.class);
    private transient final Transformer<ClusterOptions>
            clusterTransformer = Instance.singleton(ClusterStrada.class);

    private transient ClusterOptions clusterOptions;

    @Override
    public ConcurrentMap<String, VertxOptions> visit(final String... keys)
            throws ZeroException {
        // 1. Must be the first line, fixed position.
        Ensurer.eqLength(getClass(), 0, (Object[]) keys);
        // 2. Visit the node for vertx
        final JsonObject data = this.NODE.read();
        // 3. Vertx node validation.
        final JsonObject vertxData = data.getJsonObject(KEY);
        LOGGER.info(Info.INF_B_VERIFY, KEY, getClass().getSimpleName(), vertxData);
        Fn.shuntZero(() -> Ruler.verify(KEY, vertxData), vertxData);
        // 4. Set cluster options
        this.clusterOptions = this.clusterTransformer.transform(data.getJsonObject(YKEY_CLUSTERED));
        // 5. Transfer Data
        return visit(vertxData.getJsonArray(YKEY_INSTANCE));
    }

    @Override
    public ClusterOptions getCluster() {
        return this.clusterOptions;
    }

    private ConcurrentMap<String, VertxOptions> visit(
            final JsonArray vertxData)
            throws ZeroException {
        final ConcurrentMap<String, VertxOptions> map =
                new ConcurrentHashMap<>();
        final boolean clustered = this.clusterOptions.isEnabled();
        Fn.etJArray(vertxData, JsonObject.class, (item, index) -> {
            // 1. Extract single
            final String name = item.getString(YKEY_NAME);
            // 2. Extract VertxOptions
            final VertxOptions options = this.transformer.transform(item);
            // 3. Check the configuration for cluster sync
            Fn.flingZero(clustered != options.isClustered(), LOGGER,
                    ClusterConflictException.class,
                    getClass(), name, options.toString());
            // 4. Put the options into map
            map.put(name, options);
        });
        return map;
    }
}
