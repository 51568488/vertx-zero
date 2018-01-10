package io.vertx.up.aiki;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import org.junit.Test;

public class UxToTc extends StoreBase {

    @Test
    public void testUserJson(final TestContext context) {
        final JsonObject data = Ux.toJson(getEntity());
        getLogger().info("[ ZERO TEST ] Data: {0}", data);
        context.assertEquals(getEntityJson(), data);
    }

    @Test
    public void testUserPojo(final TestContext context) {
        final JsonObject data = Ux.toJson(getEntity(), "user");
        getLogger().info("[ ZERO TEST ] Pojo Data: {0}", data);
        context.assertEquals(getEntityToJson(), data);
    }

    private UserJson getEntity() {
        final UserJson json = new UserJson();
        json.setAge(12);
        json.setEmail("lang.yu@hpe.com");
        json.setName("Lang");
        return json;
    }

    private JsonObject getEntityJson() {
        return new JsonObject()
                .put("age", 12)
                .put("email", "lang.yu@hpe.com")
                .put("name", "Lang");
    }

    private JsonObject getEntityToJson() {
        return new JsonObject()
                .put("age", 12)
                .put("zEmail", "lang.yu@hpe.com")
                .put("zName", "Lang");
    }
}
