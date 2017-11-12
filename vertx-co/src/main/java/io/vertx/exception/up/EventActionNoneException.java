package io.vertx.exception.up;

import io.vertx.exception.UpException;
import io.vertx.up.ce.Event;

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
