package io.vertx.exception.up;

import io.vertx.exception.UpException;

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
