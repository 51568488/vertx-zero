package io.vertx.up.tool;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import io.vertx.up.tool.io.IO;
import org.junit.Test;

public class JacksonTc extends StoreBase {

    @Test
    public void testvJson(final TestContext context) {
        final JsonObject data = IO.getJObject(getFile("jackson-testInt.json"));
        final JsonObject hitted = Jackson.visitJObject(data, "lang", "home", "email");
        context.assertNotNull(hitted);
    }

    @Test
    public void testvInt(final TestContext context) {
        final JsonObject data = IO.getJObject(getFile("jackson-testInt.json"));
        final Integer hitted = Jackson.visitInt(data, "lang", "home", "email", "index");
        context.assertEquals(3, hitted);
    }

    @Test
    public void testMerge(final TestContext context) {
        final JsonArray fromData = IO.getJArray(getFile("from.json"));
        final JsonArray toData = IO.getJArray(getFile("to.json"));
        final JsonArray result = Jackson.mergeZip(fromData, toData, "key", "id");
        System.out.println(result);
    }

    @Test
    public void testvString(final TestContext context) {
        final JsonObject data = IO.getJObject(getFile("jackson-testInt.json"));
        final String hitted = Jackson.visitString(data, "lang", "visit");
        context.assertEquals("Home City", hitted);
    }

    @Test
    public void testvString1(final TestContext context) {
        final JsonObject data = IO.getJObject(getFile("jackson-testInt.json"));
        final String hitted = Jackson.visitString(data, "lang", "home", "deep1", "deep2", "deep3");
        context.assertEquals("Home City", hitted);
    }

    @Test
    public void testvEmpty(final TestContext context) {
        final JsonObject data = IO.getJObject(getFile("jackson-testInt.json"));
        final Integer hitted = Jackson.visitInt(data, "lang", "visitx");
        context.assertNull(hitted);
    }
}
