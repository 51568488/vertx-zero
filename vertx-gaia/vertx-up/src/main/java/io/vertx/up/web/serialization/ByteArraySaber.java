package io.vertx.up.web.serialization;

import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Values;

public class ByteArraySaber extends BaseSaber {

    @Override
    public Object from(final Class<?> paramType,
                       final String literal) {
        return Fn.get(() -> Fn.getSemi(Byte[].class == paramType ||
                        byte[].class == paramType, this.getLogger(),
                () -> literal.getBytes(Values.CHARSET), () -> new byte[0]),
                paramType, literal);
    }
}
