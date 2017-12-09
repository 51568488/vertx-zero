package io.vertx.up.kidd;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.kidd.id.ReactSpy;
import io.vertx.up.kidd.income.JArrayImitate;
import io.vertx.up.kidd.income.JObjectImitate;
import io.vertx.up.tool.mirror.Instance;

/**
 * Factory to build center controller
 */
public final class Heart {
    /**
     * Income
     */
    public static class In {
        /**
         * JsonObject for idiom
         *
         * @return
         */
        public static Imitate<JsonObject> idiom() {
            return Instance.singleton(JObjectImitate.class);
        }

        /**
         * JsonArray for imbibe
         *
         * @return
         */
        public static Imitate<JsonArray> imbibe() {
            return Instance.singleton(JArrayImitate.class);
        }
    }

    /**
     * Outcome
     */
    public static class Out {
        
    }

    public static class Id {
        /**
         * Id processor
         *
         * @return
         */
        public static Spy react() {
            return Instance.singleton(ReactSpy.class);
        }
    }
}
