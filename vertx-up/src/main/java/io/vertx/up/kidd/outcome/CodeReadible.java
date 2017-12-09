package io.vertx.up.kidd.outcome;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Readible;
import io.vertx.up.tool.io.IO;

public class CodeReadible implements Readible {
    private static final JsonObject MESSAGE = new JsonObject();

    @Override
    public void interpret(final WebException error) {
        if (MESSAGE.isEmpty()) {
            MESSAGE.mergeIn(IO.getYaml("vertx-readible.yml"));
        }
        Fn.safeNull(() -> {
            error.setReadible(MESSAGE.getString(String.valueOf(Math.abs(error.getCode()))));
        }, error);
    }
}
