package io.vertx.exception.up;

import io.vertx.exception.UpException;

public class AccessProxyException extends UpException {

    public AccessProxyException(final Class<?> clazz,
                                final Class<?> target) {
        super(clazz, target.getName());
    }

    @Override
    public int getCode() {
        return -40010;
    }
}
