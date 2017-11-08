package com.vie.core.reliable;

import com.vie.cv.Values;
import com.vie.exception.ZeroException;
import com.vie.exception.ensure.JObjectElementException;
import com.vie.hoc.HNull;
import com.vie.hoc.HTry;
import com.vie.log.Annal;
import com.vie.util.Types;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * @author lang
 */
public abstract class AbstractInsurer implements Insurer {

    @Override
    public void flumen(final JsonArray array,
                       final JsonObject elementRule)
            throws ZeroException {
        HNull.execZero(() -> {
            // 1. Verify the element must be json object for each
            final int size = array.size();
            for (int idx = Values.IDX; idx < size; idx++) {
                final Object value = array.getValue(idx);
                // 2. Call check method to confirm JsonObject
                if (!Types.isJObject(value)) {
                    HTry.execZero(getLogger(), new JObjectElementException(getClass(), idx, value));
                } else {
                    final JsonObject item = (JsonObject) value;
                    // 3. Apply the rule to each object.
                    this.flumen(item, elementRule);
                }
            }
        }, array, elementRule);
    }

    protected Annal getLogger() {
        return Annal.get(getClass());
    }
}
