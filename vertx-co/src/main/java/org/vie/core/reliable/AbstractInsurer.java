package org.vie.core.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.vie.cv.Values;
import org.vie.exception.ZeroException;
import org.vie.exception.ensure.JObjectElementException;
import org.vie.fun.HNull;
import org.vie.fun.HTry;
import org.vie.util.Types;
import org.vie.util.log.Annal;

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
