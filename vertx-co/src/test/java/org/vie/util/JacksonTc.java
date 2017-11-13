package org.vie.util;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;
import org.vie.util.io.IO;
import top.test.StoreBase;

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
