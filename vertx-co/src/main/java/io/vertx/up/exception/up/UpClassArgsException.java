package io.vertx.up.exception.up;

import io.vertx.zero.exception.UpException;

public class UpClassArgsException extends UpException {

    public UpClassArgsException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40001;
    }
}
