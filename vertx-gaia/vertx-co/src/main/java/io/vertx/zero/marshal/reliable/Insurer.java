package io.vertx.zero.marshal.reliable;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.exception.ZeroException;

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
