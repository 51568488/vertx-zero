package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;

/**
 * String
 */
public class StringSaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.get(() ->
                        Fn.getSemi(String.class == paramType, getLogger(),
                                () -> literal.toString(), () -> Strings.EMPTY),
                paramType, literal);
    }
}
