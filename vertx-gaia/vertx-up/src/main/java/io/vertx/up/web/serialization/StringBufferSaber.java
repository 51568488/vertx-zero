package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;

/**
 * StringBuffer, StringBuilder
 */
public class StringBufferSaber extends BaseSaber {
    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.get(() ->
                        Fn.getSemi(StringBuilder.class == paramType
                                        || StringBuffer.class == paramType, getLogger(),
                                () -> {
                                    if (StringBuffer.class == paramType) {
                                        return new StringBuffer(literal);
                                    } else {
                                        return new StringBuilder(literal);
                                    }
                                }, () -> Strings.EMPTY),
                paramType, literal);
    }

    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            Object reference = null;
            if (input instanceof StringBuilder
                    || input instanceof StringBuffer) {
                reference = input.toString();
            }
            return reference;
        }, input);
    }
}
