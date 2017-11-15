package io.vertx.up.exception.up;

import io.vertx.zero.exception.UpException;

public class NoArgConstructorException extends UpException {

    public NoArgConstructorException(final Class<?> clazz,
                                     final Class<?> target) {
        super(clazz, target.getName());
    }

    @Override
    public int getCode() {
        return -40009;
    }
}
