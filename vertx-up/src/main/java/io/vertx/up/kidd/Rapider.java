package io.vertx.up.kidd;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.kidd.id.ReactSpy;
import io.vertx.up.kidd.outcome.Obstain;
import io.vertx.up.tool.mirror.Instance;

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
        return Heart.idiom().request(message);
    }

    /**
     * Interface only params
     *
     * @param message
     * @return
     */
    public static JsonObject params(final Message<Envelop> message) {
        return Heart.idiom().request(message, 0);
    }

    public static Obstain<JsonObject> getReact(final Class<?> clazz) {
        final Spy<JsonObject> spy = Instance.singleton(ReactSpy.class);
        return Obstain.<JsonObject>start(clazz)
                .connect(spy);
    }
}
