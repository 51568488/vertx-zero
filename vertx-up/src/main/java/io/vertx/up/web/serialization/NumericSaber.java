package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.up.tool.mirror.Types;

import java.util.function.Function;

/**
 * Int, Long, Short
 */
public abstract class NumericSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.get(() ->
                        Fn.getSemi(isValid(paramType), getLogger(),
                                () -> {
                                    verifyInput(!Types.isInteger(literal), paramType, literal);
                                    return getFun().apply(literal);
                                }, Fn::nil),
                paramType, literal);
    }

    protected abstract boolean isValid(final Class<?> paramType);

    protected abstract <T> Function<String, T> getFun();
}
