package io.vertx.zero.exception;

public class VertxCallbackException extends UpException {

    public VertxCallbackException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40003;
    }
}
