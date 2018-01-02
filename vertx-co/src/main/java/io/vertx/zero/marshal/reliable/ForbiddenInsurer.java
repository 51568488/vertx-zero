package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.Jackson;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.demon.ForbiddenFieldException;

public class ForbiddenInsurer extends AbstractInsurer {
    /**
     * @param data
     * @param rule
     * @throws ZeroException
     * @see {
     * "forbidden":["field1","field2"]
     * }
     */
    @Override
    public void flumen(final JsonObject data, final JsonObject rule) throws ZeroException {
        // 1. If rule is null, skip
        Fn.shuntZero(() -> {
            // 2. Extract rule from config.
            if (rule.containsKey(Rules.FORBIDDEN)) {
                final JsonArray fields = Jackson.toJArray(rule.getValue(Rules.FORBIDDEN));
                Fn.etJArray(fields, String.class, (field, index) -> {
                    // 3. Check if data contains field.
                    Fn.flingZero(data.containsKey(field), getLogger(),
                            ForbiddenFieldException.class,
                            getClass(), data, field);
                });
            }
        }, rule, data);
    }
}
