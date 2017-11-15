package io.vertx.zero.tool;

import io.vertx.ext.unit.TestContext;
import io.vertx.quiz.StoreBase;
import io.vertx.zero.log.Log;
import org.junit.Test;

public class StoreTc extends StoreBase {

    @Test
    public void testJson(final TestContext context) {
        execJObject("test.json", config -> {
            Log.info(getLogger(), config.result().encode());
            context.assertTrue(config.succeeded());
        });
    }

    @Test
    public void testProp(final TestContext context) {
        execProp("test.properties", config -> {
            Log.info(getLogger(), config.result().encode());
            context.assertTrue(config.succeeded());
        });
    }
}
