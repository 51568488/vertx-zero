package io.vertx.zero.exception;

import io.vertx.core.json.JsonObject;

public class DynamicKeyMissingException extends UpException {

    public DynamicKeyMissingException(final Class<?> clazz,
                                      final String key,
                                      final JsonObject data) {
        super(clazz, key, data);
    }

    @Override
    public int getCode() {
        return -10005;
    }
}
