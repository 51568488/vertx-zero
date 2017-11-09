package com.vie.exception.up;

import com.vie.cv.em.ServerType;
import com.vie.exception.UpException;

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
