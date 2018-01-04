package io.vertx.tp.plugin.jooq;

import io.github.jklingsporn.vertx.jooq.async.future.AsyncJooqSQLClient;
import io.github.jklingsporn.vertx.jooq.async.future.VertxDAO;
import io.vertx.core.Vertx;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.plugin.Infix;
import io.vertx.up.tool.mirror.Instance;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class JooqInfix implements Infix {

    private static final String NAME = "ZERO_JOOQ_POOL";

    private static final ConcurrentMap<String, AsyncJooqSQLClient> CLIENTS
            = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Configuration> CONFIGS
            = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, VertxDAO> DAOS
            = new ConcurrentHashMap<>();
    private static Vertx vertxRef;

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        vertxRef = vertx;
        // Initialized client
        Fn.pool(CLIENTS, name,
                () -> Infix.init(Plugins.Infix.JOOQ,
                        (config) -> AsyncJooqSQLClient.create(vertx,
                                MySQLClient.createShared(vertx, config, name)),
                        JooqInfix.class));
        // Initialized default configuration
        Fn.pool(CONFIGS, name, () -> {
            final Configuration configuration = new DefaultConfiguration();
            configuration.set(SQLDialect.MYSQL_8_0);
            return configuration;
        });
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public AsyncJooqSQLClient get() {
        return getClient();
    }

    public static AsyncJooqSQLClient getClient() {
        return CLIENTS.get(NAME);
    }

    public static VertxDAO getDao(final Class<?> clazz) {
        return Fn.pool(DAOS, clazz.getName(), () -> {
            final Configuration configuration = CONFIGS.get(NAME);
            final VertxDAO dao = Instance.instance(clazz, configuration);
            dao.setClient(getClient());
            return dao;
        });
    }
}
