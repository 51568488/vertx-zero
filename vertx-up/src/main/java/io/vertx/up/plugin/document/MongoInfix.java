package io.vertx.up.plugin.document;

import io.vertx.core.Vertx;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.plugin.Infix;
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
        HPool.exec(CLIENTS, name,
                () -> Infix.init(Plugins.Infix.MONGO,
                        (config) -> MongoClient.createShared(vertx, config, name),
                        MongoInfix.class));
        System.out.println(CLIENTS);
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public MongoClient get() {
        return CLIENTS.get(NAME);
    }
}
