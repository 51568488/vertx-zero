package com.vie.core.reliable;

import com.vie.hoc.HJson;
import com.vie.hoc.HNull;
import com.vie.hoc.HTry;
import com.vie.exception.ZeroException;
import com.vie.exception.ensure.RequiredFieldException;
import com.vie.util.Jackson;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

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
        HNull.execZero(() -> {
            // 2. extract rule from rule, only required accept
            if (rule.containsKey(Rules.REQUIRED)) {
                final JsonArray fields = Jackson.toJArray(rule.getValue(Rules.REQUIRED));
                HJson.execZero(fields, String.class, (field, index) -> {
                    // 3.Check if data contains field.
                    if (!data.containsKey(field)) {
                        // Fast throw out
                        HTry.execZero(getLogger(),
                                new RequiredFieldException(getClass(), data, field));
                    }
                });
            }
        }, rule, data);
    }
}
