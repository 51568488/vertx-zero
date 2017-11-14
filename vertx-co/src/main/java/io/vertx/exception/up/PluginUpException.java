package io.vertx.exception.up;

import io.vertx.exception.WebException;

public class PluginUpException extends WebException {

    public PluginUpException(final Class<?> clazz,
                             final String name,
                             final String message) {
        super(clazz, name, message);
    }

    @Override
    public int getCode() {
        return -40019;
    }
}
