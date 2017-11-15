package io.vertx.up.exception.up;

import io.vertx.zero.exception.UpException;

public class PathAnnoEmptyException extends UpException {

    public PathAnnoEmptyException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40006;
    }
}
