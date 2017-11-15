package io.vertx.up.exception.up;

import io.vertx.zero.exception.WebException;

public class PluginSpecificationException extends WebException {

    public PluginSpecificationException(final Class<?> clazz,
                                        final String key) {
        super(clazz, key);
    }

    @Override
    public int getCode() {
        return -40016;
    }
}
