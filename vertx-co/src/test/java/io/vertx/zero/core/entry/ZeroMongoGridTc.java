package io.vertx.zero.core.entry;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import top.UnitBase;

public class ZeroMongoGridTc extends UnitBase {
    final JsonObject options = ZeroMongoGrid.options();

    @Test
    public void port_is_2008(final TestContext ctx) {
        ctx.assertEquals(2008, options.getInteger("port"));
    }

    @Test
    public void db_name_is_mongo_test_db(final TestContext ctx) {
        ctx.assertEquals("mongo_test_db", options.getString("db_name"));
    }

    @Test
    public void host_is_127003(final TestContext ctx) {
        ctx.assertEquals("127.0.0.3", options.getString("host"));
    }
}
