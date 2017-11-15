package io.vertx.up.exception;

import io.vertx.up.atom.Event;
import io.vertx.zero.exception.UpException;

public class EventActionNoneException extends UpException {

    public EventActionNoneException(final Class<?> clazz,
                                    final Event event) {
        super(clazz, event.getPath());
    }

    @Override
    public int getCode() {
        return -40008;
    }
}
