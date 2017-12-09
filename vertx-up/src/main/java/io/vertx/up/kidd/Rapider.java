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
@SuppressWarnings("unchecked")
public class Rapider {
    /**
     * Body params
     *
     * @param message
     * @return
     */
    public static JsonObject getBody(final Message<Envelop> message) {
        return Heart.idiom().request(message);
    }

    /**
     * Interface only params
     *
     * @param message
     * @return
     */
    public static JsonObject getJson(final Message<Envelop> message) {
        return Heart.idiom().request(message, 0);
    }

    /**
     * @param message
     * @return
     */
    public static String getString(final Message<Envelop> message, final int index) {
        return Heart.iambic().request(message, index);
    }

    public static Obstain<JsonObject> getReact(final Class<?> clazz) {
        final Spy<JsonObject> spy = Instance.singleton(ReactSpy.class);
        return Obstain.<JsonObject>start(clazz)
                .connect(spy);
    }
}
