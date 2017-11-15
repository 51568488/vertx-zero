package io.vertx.up.exception.up;

import io.vertx.zero.exception.UpException;

public class MethodNullException extends UpException {

    public MethodNullException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40007;
    }
}
