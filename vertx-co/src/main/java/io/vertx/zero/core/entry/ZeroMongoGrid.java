package io.vertx.zero.core.entry;

import com.vie.util.Instance;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.config.ZeroPlugin;

/**
 * @author Dean Zhao
 * 读取vertx-mongo.yml配置文件
 */
public class ZeroMongoGrid {
    private static final String MONGO = "mongo";

    private ZeroMongoGrid() {
    }

    /**
     * 读取vertx-mongo.yml, 作为MongoClient的Options
     */
    public static JsonObject options() {
        final ZeroPlugin zeroPlugin = Instance.singleton(ZeroPlugin.class, MONGO);
        return zeroPlugin.read();
    }
}
