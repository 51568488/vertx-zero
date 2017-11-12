package io.vertx.exception.up;

import io.vertx.exception.UpException;
import io.vertx.up.cv.em.ServerType;

public class AgentDuplicatedException extends UpException {

    public AgentDuplicatedException(final Class<?> clazz,
                                    final ServerType type,
                                    final int numbber) {
        super(clazz, numbber, type);
    }

    @Override
    public int getCode() {
        return -40004;
    }
}
