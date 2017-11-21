package io.vertx.up.rs.regular;

import io.vertx.up.atom.Rule;
import io.vertx.up.exception.WebException;

public interface Ruler {
    /**
     * Verify each field for @BodyParam
     *
     * @param field
     * @param value
     * @param rule
     * @return
     */
    WebException verify(final String field,
                        final Object value,
                        final Rule rule);

    static Ruler get(final String type) {
        return Pool.RULERS.get(type);
    }
}
