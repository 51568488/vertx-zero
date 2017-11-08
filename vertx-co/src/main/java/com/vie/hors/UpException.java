package com.vie.hors;

import com.vie.cv.Strings;
import com.vie.util.Errors;

/**
 * Top Exception for error code mapping ( Runtime )
 */
public abstract class UpException extends ZeroRunException {
    private final String message;

    public UpException(final Class<?> clazz, final Object... args) {
        super(Strings.EMPTY);
        this.message = Errors.normalize(clazz, getCode(), args);
    }

    public abstract int getCode();

    @Override
    public String getMessage() {
        return this.message;
    }
}
