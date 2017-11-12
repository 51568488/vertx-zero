package io.vertx.exception.up;

import io.vertx.exception.UpException;

public class VertxCallbackException extends UpException {

    public VertxCallbackException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40003;
    }
}
