package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;

public class XtorConnectException extends UpException {

    public XtorConnectException(final Class<?> clazz,
                                final String component,
                                final String method) {
        super(clazz, component, method);
    }

    @Override
    public int getCode() {
        return -40033;
    }
}
