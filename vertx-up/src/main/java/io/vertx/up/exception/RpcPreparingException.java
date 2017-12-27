package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;

public class RpcPreparingException extends UpException {

    public RpcPreparingException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40037;
    }
}
