package io.vertx.exception.zero;

import org.vie.exception.DemonException;

/**
 * Server config:
 * server:
 * -
 */
public class ServerConfigException extends DemonException {
    public ServerConfigException(final Class<?> clazz,
                                 final String config) {
        super(clazz, config);
    }

    @Override
    public int getCode() {
        return -30001;
    }
}
