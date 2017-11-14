package io.vertx.exception.up;

import io.vertx.exception.WebException;

public class AsyncSignatureException extends WebException {

    public AsyncSignatureException(final Class<?> clazz,
                                   final String returnType,
                                   final String paramType) {
        super(clazz, returnType, paramType);
    }

    @Override
    public int getCode() {
        return -40018;
    }
}
