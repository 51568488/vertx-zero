package io.vertx.tp.hikari;

import com.zaxxer.hikari.pool.HikariPool;
import org.junit.Test;

public class HikariCpTc {

    @Test
    public void testCp() {
        final HikariPool pool = HikariCpPool.getPool();
    }
}