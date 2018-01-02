package io.vertx.zero.exception;

public class RpcPreparingException extends UpException {

    public RpcPreparingException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40037;
    }
}
