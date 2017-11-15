package io.vertx.up.exception.up;

import io.vertx.zero.exception.UpException;

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
