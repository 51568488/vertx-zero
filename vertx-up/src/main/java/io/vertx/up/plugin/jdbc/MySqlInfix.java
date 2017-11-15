package io.vertx.up.plugin.jdbc;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.plugin.Infix;
import io.vertx.up.web.ZeroGrid;
import io.vertx.zero.func.HPool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class MySqlInfix implements Infix {

    private static final String NAME = "ZERO_MYSQL_POOL";

    private static final ConcurrentMap<String, SQLClient> CLIENTS
            = new ConcurrentHashMap<>();

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        final JsonObject config = ZeroGrid.getOptions("mysql");
        HPool.exec(CLIENTS, name,
                () -> MySQLClient.createShared(vertx, config, name));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public SQLClient get() {
        return CLIENTS.get(NAME);
    }
}
