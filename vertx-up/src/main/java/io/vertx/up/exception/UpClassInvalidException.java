package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;

public class UpClassInvalidException extends UpException {

    public UpClassInvalidException(final Class<?> clazz,
                                   final String className) {
        super(clazz, className);
    }

    @Override
    public int getCode() {
        return -40002;
    }
}
