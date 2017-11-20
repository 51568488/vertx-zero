package io.vertx.zero.exception;

public class DuplicatedImplException extends UpException {

    public DuplicatedImplException(final Class<?> clazz,
                                   final Class<?> interfaceCls) {
        super(clazz, interfaceCls.getName());
    }

    @Override
    public int getCode() {
        return -40028;
    }
}
