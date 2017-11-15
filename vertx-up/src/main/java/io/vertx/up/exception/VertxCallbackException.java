package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;

public class VertxCallbackException extends UpException {

    public VertxCallbackException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40003;
    }
}
