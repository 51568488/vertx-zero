package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;

public class QiyExecuteException extends WebException {

    public QiyExecuteException(final Class<?> clazz,
                               final String code,
                               final String message) {
        super(clazz, code, message);
    }

    @Override
    public int getCode() {
        return -20001;
    }

    @Override
    public HttpStatusCode getStatus() {
        return HttpStatusCode.UNAUTHORIZED;
    }
}
