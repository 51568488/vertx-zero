package io.vertx.zero.core.config;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.ZeroNode;
import org.vie.cv.FileTypes;
import org.vie.cv.Strings;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Storage {
    /**
     * Nodes
     */
    ConcurrentMap<String, ZeroNode<JsonObject>> NODES = new ConcurrentHashMap<>();
    /**
     * Lime Data
     */
    ConcurrentMap<String, String> DATA_LIME = new ConcurrentHashMap<String, String>() {
        {
            put("error", Path.ERROR);
            put("inject", Path.INJECT);
            put("server", Path.SERVER);
            put("failure", Path.FAILURE);
        }
    };
}

interface Path {
    /**
     * The root vertx file
     */
    String KE_VERTX = "vertx"
            + Strings.DOT + FileTypes.YML;

    String ERROR = Limes.ERROR
            + Strings.DOT + FileTypes.YML;

    String INJECT = Limes.INJECT
            + Strings.DOT + FileTypes.YML;

    String SERVER = Limes.SERVER
            + Strings.DOT + FileTypes.YML;

    String FAILURE = Limes.FAILURE
            + Strings.DOT + FileTypes.YML;
}

interface Key {
    /**
     * Vertx Zero configuration
     */
    String ZERO = "zero";
    /**
     * External Zero configuration
     */
    String LIME = "lime";
}

interface Limes {

    String PREFIX = "vertx";

    String ERROR = PREFIX + Strings.DASH + "error";

    String INJECT = PREFIX + Strings.DASH + "inject";

    String SERVER = PREFIX + Strings.DASH + "server";

    String FAILURE = PREFIX + Strings.DASH + "failure";
}
