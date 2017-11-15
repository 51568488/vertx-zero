package io.vertx.up.infinite.mongo;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.plugin.Infix;
import io.vertx.up.web.ZeroGrid;
import io.vertx.zero.func.HPool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
@Plugin
@SuppressWarnings("unchecked")
public class MongoInfix implements Infix {

    private static final String NAME = "ZERO_MONGO_POOL";
    /**
     * All Configs
     **/
    private static final ConcurrentMap<String, MongoClient> CLIENTS
            = new ConcurrentHashMap<>();

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        final JsonObject config = ZeroGrid.getOptions("mongo");
        HPool.exec(CLIENTS, name,
                () -> MongoClient.createShared(vertx, config, name));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public MongoClient get() {
        return CLIENTS.get(NAME);
    }
}
