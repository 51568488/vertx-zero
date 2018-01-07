package io.vertx.zero.exception;

public class EtcdConfigEmptyException extends UpException {

    public EtcdConfigEmptyException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40027;
    }
}
