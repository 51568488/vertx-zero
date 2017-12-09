package io.vertx.up.kidd;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;

/**
 * Fast tool to extract params
 */
public class Rapider {
    /**
     * Body params
     *
     * @param message
     * @return
     */
    public static JsonObject body(final Message<Envelop> message) {
        return Heart.In.idiom().request(message);
    }

    /**
     * Interface only params
     *
     * @param message
     * @return
     */
    public static JsonObject params(final Message<Envelop> message) {
        return Heart.In.idiom().request(message, 0);
    }
}
