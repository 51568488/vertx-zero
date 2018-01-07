package io.vertx.zero.exception;

import io.vertx.zero.log.Errors;

/**
 * Top Exception for error code mapping.
 */
public abstract class DemonException extends ZeroException {

    private final String message;

    public DemonException(final Class<?> clazz, final Object... args) {
        super(null);
        this.message = Errors.normalize(clazz, getCode(), args);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
