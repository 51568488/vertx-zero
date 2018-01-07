package io.vertx.tp.hikari.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import io.vertx.core.json.JsonObject;

/**
 * JsonObject -> HikariConfig
 */
public class HikariCpConfig {

    private transient final HikariPool pool;


    public static HikariCpConfig create(final JsonObject config) {
        return new HikariCpConfig(config);
    }

    private HikariCpConfig(final JsonObject config) {

        final HikariConfig hikariConfig = new HikariConfig();
        // Init hikariCp, refer to offical site
        // https://github.com/brettwooldridge/HikariCP
        hikariConfig.setDriverClassName(config.getString("driverClassName"));
        hikariConfig.setJdbcUrl(config.getString("jdbcUrl"));
        hikariConfig.setUsername(config.getString("username"));
        hikariConfig.setPassword(config.getString("password"));
        hikariConfig.setCatalog(config.getString("catalog"));
        // Init data source
        final HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        this.pool = new HikariPool(dataSource);
    }

    public HikariPool getPool() {
        return this.pool;
    }
}
