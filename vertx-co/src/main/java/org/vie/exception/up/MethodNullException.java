package org.vie.exception.up;

import org.vie.exception.UpException;

public class MethodNullException extends UpException {

    public MethodNullException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40007;
    }
}
