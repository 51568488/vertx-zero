package io.vertx.up.kidd.outcome;

import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.Readible;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.io.IO;
import io.vertx.up.tool.io.Stream;

import java.io.InputStream;

public class CodeReadible implements Readible {
    private static final Annal LOGGER = Annal.get(CodeReadible.class);
    private static final JsonObject MESSAGE = new JsonObject();
    private static final String FILENAME = "vertx-readible.yml";

    @Override
    public void interpret(final WebException error) {
        if (MESSAGE.isEmpty()) {
            final InputStream in = Stream.in(FILENAME);
            // Do not throw out EmptyStreamException when file does not existing.
            if (null != in) {
                MESSAGE.mergeIn(IO.getYaml(FILENAME));
            }
        }
        // Pick up message from MESSAGE cache.
        final String message = MESSAGE.getString(String.valueOf(Math.abs(error.getCode())));
        // Check whether the readible set.
        Fn.safeSemi(StringUtil.isNil(error.getReadible()), LOGGER,
                () -> Fn.safeNull(() -> error.setReadible(message), error));
    }
}
