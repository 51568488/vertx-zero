package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.StoreBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UxArrayTc extends StoreBase {

    @Test
    public void testArray() {
        final JsonArray source = getArray("source.json");
        final JsonArray target = getArray("target.json");
        final JsonArray result =
                Uarr.create(source).zip(target, "key", "roomId").to();
        System.err.println(result.encodePrettily());
        for (int idx = 0; idx < result.size(); idx++) {
            final JsonObject item = result.getJsonObject(idx);
            Assert.assertNotNull(item);
        }
    }

    @Test
    public void testGrouped() {
        final JsonArray source = getArray("source.json");
        final JsonObject data = Ux.toJsonByGroup(source, "floorId");
        System.out.println(data);
    }

    @Test
    public void testPojo() {
        final List<UserJson> user = new ArrayList<>();
        final UserJson json = new UserJson();
        json.setAge(13);
        json.setEmail("lang.yu@hpe.com");
        json.setName("Lang.Yu");
        user.add(json);
        Ux.thenJsonMore(user, "").setHandler(item -> {
            System.out.println(item.result());
        });
    }
}
