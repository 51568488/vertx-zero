package io.vertx.up.exception;

import io.vertx.zero.exception.WebException;

import java.lang.reflect.Method;

public class AddressWrongException extends WebException {

    public AddressWrongException(final Class<?> clazz,
                                 final String address,
                                 final Class<?> target,
                                 final Method method) {
        super(clazz, address, target.getName(), method.getName());
    }

    @Override
    public int getCode() {
        return -40012;
    }
}
