package io.vertx.up.tool;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import io.vertx.up.kidd.unseen.Apeak;
import io.vertx.up.tool.io.IO;
import org.junit.Test;

public class VerticalTc extends StoreBase {

    @Test
    public void testJson(final TestContext context) {
        final JsonObject data = IO.getJObject(getFile("json-obj.json"));
        final JsonObject processed = Vertical.to(data, Apeak.create("_id", "key"));
        System.out.println(processed.encode());
        context.assertNotNull(processed);
    }

    @Test
    public void testArray(final TestContext context) {
        final JsonArray data = IO.getJArray(getFile("json-arr.json"));
        final JsonArray processed = Vertical.to(data, Apeak.create("_id", "key"));
        System.out.println(processed.encode());
        context.assertNotNull(processed);
    }
}
