package io.vertx.exception.up;

import io.vertx.exception.UpException;

public class UpClassArgsException extends UpException {

    public UpClassArgsException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40001;
    }
}
