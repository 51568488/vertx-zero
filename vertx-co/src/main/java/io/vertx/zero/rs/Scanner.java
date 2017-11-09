package io.vertx.zero.rs;

import io.vertx.core.json.JsonObject;

/**
 * Scanner for different entity of jsr311
 *
 * @author Lang
 */
public interface Scanner {
    /**
     * Read the meta data for different annotations.
     *
     * @return
     */
    JsonObject getMeta();
}
