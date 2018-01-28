package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.quiz.StoreBase;
import org.junit.Assert;
import org.junit.Test;

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
}
