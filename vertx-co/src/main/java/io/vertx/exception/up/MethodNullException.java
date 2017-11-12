package io.vertx.exception.up;

import io.vertx.exception.UpException;

public class MethodNullException extends UpException {

    public MethodNullException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40007;
    }
}
