package io.vertx.zero.exception;

public class UniqueImplMissingException extends UpException {

    public UniqueImplMissingException(final Class<?> clazz,
                                      final Class<?> interfaceCls) {
        super(clazz, interfaceCls.getName());
    }

    @Override
    public int getCode() {
        return -40027;
    }
}
