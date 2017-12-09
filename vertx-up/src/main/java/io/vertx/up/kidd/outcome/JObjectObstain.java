package io.vertx.up.kidd.outcome;

import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonObject;
import io.vertx.up.kidd.Obstain;
import io.vertx.up.kidd.Spy;

/**
 * Unique object response.
 */
public class JObjectObstain implements Obstain<JsonObject> {

    @Override
    public void response(final AsyncResult<JsonObject> handler,
                         final Spy<JsonObject> spy) {
    }
}
