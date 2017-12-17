package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;

public class XtorExecuteException extends UpException {

    public XtorExecuteException(final Class<?> clazz,
                                final String details) {
        super(clazz, details);
    }

    @Override
    public int getCode() {
        return -40035;
    }
}
