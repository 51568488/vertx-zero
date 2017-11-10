package org.vie.exception.up;

import io.vertx.up.ce.Event;
import org.vie.exception.UpException;

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
