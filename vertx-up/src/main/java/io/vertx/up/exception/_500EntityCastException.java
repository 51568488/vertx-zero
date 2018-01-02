package io.vertx.up.exception;

public class _500EntityCastException extends WebException {

    public _500EntityCastException(final Class<?> clazz,
                                   final String address,
                                   final String message) {
        super(clazz, address, message);
    }

    @Override
    public int getCode() {
        return -60003;
    }
}
