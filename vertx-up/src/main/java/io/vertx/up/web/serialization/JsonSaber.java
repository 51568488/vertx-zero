package io.vertx.up.web.serialization;

import io.vertx.core.json.DecodeException;
import io.vertx.up.exception.web._400ParameterFromStringException;
import io.vertx.up.func.Fn;

import java.util.function.Function;

/**
 * Json
 */
public abstract class JsonSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.get(() ->
                        Fn.getSemi(isValid(paramType), getLogger(),
                                () -> {
                                    try {
                                        return getFun().apply(literal);
                                    } catch (final DecodeException ex) {
                                        getLogger().jvm(ex);
                                        throw new _400ParameterFromStringException(getClass(), paramType, literal);
                                    }
                                }, Fn::nil),
                paramType, literal);
    }

    protected abstract boolean isValid(final Class<?> paramType);

    protected abstract <T> Function<String, T> getFun();
}
