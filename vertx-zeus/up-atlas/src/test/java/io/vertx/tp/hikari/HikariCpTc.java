package io.vertx.tp.hikari;

import java.sql.Connection;

public class HikariCpTc {

    public void testCp() {
        final Connection connection = HikariCpPool.getConnection();
    }
}