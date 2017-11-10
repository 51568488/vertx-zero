package org.vie.exception.up;

import org.vie.cv.em.ServerType;
import org.vie.exception.UpException;

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
