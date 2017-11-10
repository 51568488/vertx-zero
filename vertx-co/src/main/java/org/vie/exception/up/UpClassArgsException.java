package org.vie.exception.up;

import org.vie.exception.UpException;

public class UpClassArgsException extends UpException {

    public UpClassArgsException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40001;
    }
}
