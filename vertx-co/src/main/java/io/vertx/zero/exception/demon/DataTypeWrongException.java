package io.vertx.zero.exception.demon;

import io.vertx.zero.eon.em.DataType;
import io.vertx.zero.exception.DemonException;

public class DataTypeWrongException extends DemonException {

    public DataTypeWrongException(final Class<?> clazz,
                                  final String field,
                                  final Object value,
                                  final DataType type) {
        super(clazz, field, value, type);
    }

    @Override
    public int getCode() {
        return -10003;
    }
}
