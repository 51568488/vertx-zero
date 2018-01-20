package io.vertx.tp.plugin.jooq;

import io.vertx.core.Vertx;
import io.vertx.tp.hikari.HikariCpPool;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.func.Fn;
import io.vertx.up.plugin.Infix;
import io.vertx.up.tool.mirror.Instance;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultConnectionProvider;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin
@SuppressWarnings("unchecked")
public class JooqInfix implements Infix {

    private static final String NAME = "ZERO_JOOQ_POOL";

    private static final ConcurrentMap<String, Configuration> CONFIGS
            = new ConcurrentHashMap<>();

    private static Vertx vertxRef;

    private static void initInternal(final Vertx vertx,
                                     final String name) {
        vertxRef = vertx;
        Fn.pool(CONFIGS, name,
                () -> Infix.init(Plugins.Infix.JOOQ,
                        (config) -> {
                            // Initialized client
                            final Configuration configuration = new DefaultConfiguration();
                            configuration.set(SQLDialect.MYSQL_8_0);
                            final ConnectionProvider provider =
                                    new DefaultConnectionProvider(HikariCpPool.getConnection(
                                            config.getJsonObject("provider")
                                    ));
                            // Initialized default configuration
                            configuration.set(provider);
                            return configuration;
                        }, JooqInfix.class));
    }

    public static void init(final Vertx vertx) {
        initInternal(vertx, NAME);
    }

    @Override
    public Configuration get() {
        return CONFIGS.get(NAME);
    }

    public static <T> T getDao(final Class<T> clazz) {
        final T dao = Instance.instance(clazz, CONFIGS.get(NAME));
        Instance.invoke(dao, "setVertx", vertxRef);
        return dao;
    }

    public static DSLContext getDSL() {
        final Configuration configuration = CONFIGS.get(NAME);
        return configuration.dsl();
    }
}
