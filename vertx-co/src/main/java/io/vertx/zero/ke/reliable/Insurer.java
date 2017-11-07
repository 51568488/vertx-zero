package io.vertx.zero.ke.reliable;

import com.vie.hors.ZeroException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Insure to be configuration correct.
 */
public interface Insurer {
    /**
     * @param data
     * @throws ZeroException
     */
    void flumen(JsonObject data, JsonObject rule) throws ZeroException;

    /**
     * Verify json array for each element.
     *
     * @param array
     * @param elementRule
     * @throws ZeroException
     */
    void flumen(JsonArray array, JsonObject elementRule) throws ZeroException;
}
