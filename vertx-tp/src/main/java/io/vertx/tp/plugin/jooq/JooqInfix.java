package io.vertx.tp.plugin.jooq;

import io.github.jklingsporn.vertx.jooq.async.future.AsyncJooqSQLClient;
import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.plugin.Infix;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class JooqInfix implements Infix {

    private static final String NAME = "ZERO_JOOQ_POOL";

    private static final ConcurrentMap<String, AsyncJooqSQLClient> CLIENTS
            = new ConcurrentHashMap<>();

    private static void initInternal(final Vertx vertx,
                                     final String name){
        Fn.pool(CLIENTS, name,
                () -> Infix.init(Plugins.Infix.JOOQ,
                        (config) -> AsyncJooqSQLClient.create(vertx,
                                MySQLClient.createShared(vertx, config, name)),
                        JooqInfix.class));
    }

    public static void init(final Vertx vertx){
        initInternal(vertx, NAME);
    }

    @Override
    public AsyncJooqSQLClient get() {
        return getClient();
    }

    public static AsyncJooqSQLClient getClient(){
        return CLIENTS.get(NAME);
    }
}
