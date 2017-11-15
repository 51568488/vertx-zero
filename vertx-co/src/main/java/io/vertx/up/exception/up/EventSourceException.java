package io.vertx.up.exception.up;

import io.vertx.zero.exception.UpException;

public class EventSourceException extends UpException {

    public EventSourceException(final Class<?> clazz,
                                final String endpointCls) {
        super(clazz, endpointCls);
    }

    @Override
    public int getCode() {
        return -40005;
    }
}
