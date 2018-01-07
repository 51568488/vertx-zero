package io.vertx.quiz;

import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.up.tool.io.IO;
import io.vertx.zero.log.Log;

public class TestBase {

    protected String getFile(final String filename) {
        final Class<?> clazz = getClass();
        final String file = "test/" + clazz.getPackage().getName() + "/" + filename;
        Log.info(getLogger(), "[TEST] Test input file: {0}", file);
        return file;
    }

    protected JsonObject getJson(final String filename) {
        return IO.getJObject(getFile(filename));
    }

    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }
}
