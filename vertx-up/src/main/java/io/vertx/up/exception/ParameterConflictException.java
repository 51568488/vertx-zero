package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;

public class ParameterConflictException extends UpException {

    public ParameterConflictException(final Class<?> clazz,
                                      final Class<?> target) {
        super(clazz, target);
    }

    @Override
    public int getCode() {
        return -40011;
    }
}
