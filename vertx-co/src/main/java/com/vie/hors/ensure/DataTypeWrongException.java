package com.vie.hors.ensure;

import com.vie.em.DataType;
import com.vie.hors.DemonException;

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
