package io.vertx.up.exception;

public class _400BadRequestException extends WebException {

    public _400BadRequestException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -60011;
    }
}
