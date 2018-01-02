package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;

public class _404RecordNotFoundException extends WebException {

    public _404RecordNotFoundException(final Class<?> clazz) {
        super(clazz);
        this.status = HttpStatusCode.NOT_FOUND;
    }

    @Override
    public int getCode() {
        return -60008;
    }

    @Override
    public HttpStatusCode getStatus() {
        return this.status;
    }
}
