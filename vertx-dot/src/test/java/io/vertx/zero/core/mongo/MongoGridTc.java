package io.vertx.zero.core.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.zero.web.ZeroGrid;
import org.junit.Test;
import org.vie.util.Types;
import top.test.UnitBase;

public class MongoGridTc extends UnitBase {
    @Test
    public void testMongo(final TestContext context) {
        final JsonObject data = ZeroGrid.getOptions("mongo");
        System.out.println(data);
        context.assertNotNull(data);
        context.assertTrue(Types.isJObject(data));
    }
}
