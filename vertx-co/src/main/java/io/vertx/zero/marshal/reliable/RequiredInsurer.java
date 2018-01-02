package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.Jackson;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.RequiredFieldException;

/**
 * Required validation
 */
public class RequiredInsurer extends AbstractInsurer {
    /**
     * @param data
     * @param rule
     * @throws ZeroException
     * @see {
     * "required":["field1","field2"]
     * }
     */
    @Override
    public void flumen(final JsonObject data, final JsonObject rule) throws ZeroException {
        // 1. If rule is null, skip
        Fn.shuntZero(() -> {
            // 2. extract rule from rule, only required accept
            if (rule.containsKey(Rules.REQUIRED)) {
                final JsonArray fields = Jackson.toJArray(rule.getValue(Rules.REQUIRED));
                Fn.etJArray(fields, String.class, (field, index) -> {
                    // 3.Check if data contains field.
                    // Fast throw out
                    Fn.flingZero(!data.containsKey(field), getLogger(),
                            RequiredFieldException.class,
                            getClass(), data, field);
                });
            }
        }, rule, data);
    }
}
