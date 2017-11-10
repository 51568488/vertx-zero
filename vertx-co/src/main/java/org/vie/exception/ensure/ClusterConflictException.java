package org.vie.exception.ensure;

import org.vie.exception.DemonException;

public class ClusterConflictException extends DemonException {

    public ClusterConflictException(final Class<?> clazz,
                                    final String name,
                                    final String options) {
        super(clazz, name, options);
    }

    @Override
    public int getCode() {
        return -10004;
    }
}
