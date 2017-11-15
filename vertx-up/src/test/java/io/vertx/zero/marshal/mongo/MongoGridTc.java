package io.vertx.zero.marshal.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.up.web.ZeroGrid;
import io.vertx.zero.test.UnitBase;
import io.vertx.zero.tool.mirror.Types;
import org.junit.Test;

public class MongoGridTc extends UnitBase {
    @Test
    public void testMongo(final TestContext context) {
        final JsonObject data = ZeroGrid.getOptions("mongo");
        System.out.println(data);
        context.assertNotNull(data);
        context.assertTrue(Types.isJObject(data));
    }
}
