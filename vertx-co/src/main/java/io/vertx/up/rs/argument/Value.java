package io.vertx.up.rs.argument;

import org.vie.util.Types;

class Value {

    static Object get(final String value, final Class<?> paramType) {
        Object returnValue = null;
        if (null != value) {
            returnValue = Types.fromString(paramType, value);
        }
        return returnValue;
    }
}
