package com.vie.exception.up;

import com.vie.exception.UpException;

public class VertxCallbackException extends UpException {

    public VertxCallbackException(final Class<?> clazz) {
        super(clazz);
    }

    @Override
    public int getCode() {
        return -40003;
    }
}
