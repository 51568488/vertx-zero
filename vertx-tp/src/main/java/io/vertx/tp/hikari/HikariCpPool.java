package io.vertx.tp.hikari;

import com.zaxxer.hikari.pool.HikariPool;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.hikari.config.HikariCpConfig;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

/**
 * Enabled hikari pool
 */
public class HikariCpPool {

    private static final Annal LOGGER = Annal.get(HikariCpPool.class);
    private static final Node<JsonObject> NODE = Instance.singleton(ZeroUniform.class);
    private static HikariCpConfig CONFIG;

    private static final String KEY = "pool";
    private static final String HIKARI_KEY = "hikari";

    static {
        final JsonObject config = NODE.read();
        Fn.safeSemi(null != config && config.containsKey(KEY), LOGGER, () -> {
            final JsonObject hitted = config.getJsonObject(KEY);
            Fn.safeSemi(null != hitted && hitted.containsKey(HIKARI_KEY), LOGGER, () -> {
                final JsonObject meta = hitted.getJsonObject(HIKARI_KEY);
                Fn.flingUp(() -> Fn.shuntZero(() ->
                                Ruler.verify(HIKARI_KEY, meta), meta),
                        LOGGER);
                CONFIG = HikariCpConfig.create(meta);
            });
        });
    }

    public static HikariPool getPool() {
        return CONFIG.getPool();
    }
}
