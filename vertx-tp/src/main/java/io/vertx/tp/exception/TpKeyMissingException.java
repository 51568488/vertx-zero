package io.vertx.tp.exception;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.exception.UpException;

public class TpKeyMissingException extends UpException {

    public TpKeyMissingException(final Class<?> clazz,
                                 final String key,
                                 final JsonObject config) {
        super(clazz, key, config.encode());
    }

    @Override
    public int getCode() {
        return -30002;
    }
}
