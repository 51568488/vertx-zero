package io.vertx.zero.exception;

public class XtorNotReadyException extends UpException {

    public XtorNotReadyException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40034;
    }
}
