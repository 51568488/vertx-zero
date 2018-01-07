package io.vertx.up.kidd;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.kidd.income.IntImitate;
import io.vertx.up.kidd.income.JObjectImitate;
import io.vertx.up.kidd.income.StringImitate;
import io.vertx.up.tool.mirror.Instance;

/**
 * Fast tool to extract params
 */
@SuppressWarnings("unchecked")
public class Rapider {
    /**
     * Get data from event bus directly or body request.
     *
     * @param message
     * @return
     */
    public static JsonObject getData(final Message<Envelop> message) {
        final Imitate<JsonObject> imitate = Instance.singleton(JObjectImitate.class);
        return imitate.request(message);
    }

    public static JsonObject getJson(final Message<Envelop> message) {
        final Imitate<JsonObject> imitate = Instance.singleton(JObjectImitate.class);
        return imitate.request(message, 0);
    }

    public static String getString(final Message<Envelop> message, final int index) {
        final Imitate<String> imitate = Instance.singleton(StringImitate.class);
        return imitate.request(message, index);
    }

    public static Integer getInt(final Message<Envelop> message, final int index) {
        final Imitate<Integer> imitate = Instance.singleton(IntImitate.class);
        return imitate.request(message, index);
    }
}
