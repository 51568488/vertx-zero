package io.vertx.zero.exception.demon;

import io.vertx.zero.exception.DemonException;

public class JObjectElementException extends DemonException {

    public JObjectElementException(final Class<?> clazz,
                                   final int index,
                                   final Object value) {
        super(clazz, index, value);
    }

    @Override
    public int getCode() {
        return -10001;
    }
}
