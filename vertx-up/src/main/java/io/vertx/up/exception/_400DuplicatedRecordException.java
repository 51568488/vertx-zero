package io.vertx.up.exception;

public class _400DuplicatedRecordException extends WebException {

    public _400DuplicatedRecordException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -60009;
    }
}
