package io.vertx.up.web.serialization;

import io.vertx.core.json.JsonArray;
import io.vertx.up.func.Fn;
import io.vertx.zero.tool.Jackson;

import java.util.Collection;

/**
 * Enum
 */
public class CollectionSaber extends BaseSaber {
    @Override
    public <T> Object from(final T input) {
        return Fn.get(() -> {
            Object reference = null;
            if (input instanceof Collection ||
                    input.getClass().isArray()) {
                final String literal = Jackson.serialize(input);
                reference = new JsonArray(literal);
            }
            return reference;
        }, input);
    }
}
