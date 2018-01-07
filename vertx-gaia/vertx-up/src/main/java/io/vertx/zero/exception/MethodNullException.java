package io.vertx.zero.exception;

public class MethodNullException extends UpException {

    public MethodNullException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40007;
    }
}
