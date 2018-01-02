package io.vertx.zero.exception;

public class PathAnnoEmptyException extends UpException {

    public PathAnnoEmptyException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40006;
    }
}
