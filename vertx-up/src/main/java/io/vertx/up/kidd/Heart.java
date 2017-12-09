package io.vertx.up.kidd;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.kidd.income.JArrayImitate;
import io.vertx.up.kidd.income.JObjectImitate;
import io.vertx.up.tool.mirror.Instance;

/**
 * Factory to build center controller
 */
final class Heart {
    /**
     * JsonObject for idiom
     *
     * @return
     */
    static Imitate<JsonObject> idiom() {
        return Instance.singleton(JObjectImitate.class);
    }

    /**
     * JsonArray for imbibe
     *
     * @return
     */
    static Imitate<JsonArray> imbibe() {
        return Instance.singleton(JArrayImitate.class);
    }
}
