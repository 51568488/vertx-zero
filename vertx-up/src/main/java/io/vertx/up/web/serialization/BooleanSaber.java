package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.zero.tool.mirror.Types;

/**
 * Boolean
 */
public class BooleanSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.getSemi(boolean.class == paramType || Boolean.class == paramType, getLogger(),
                () -> {
                    verifyInput(!Types.isDecimal(literal), paramType, literal);
                    return Boolean.valueOf(literal);
                }, () -> Boolean.FALSE);
    }
}
