package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Read options and set default values
 *
 * @param <T>
 */
public interface Node<T> {

    T read();

    /**
     * Infix usage for dynamic configuraiton laoding.
     *
     * @param key
     * @return
     */
    ConcurrentMap<String, Node<JsonObject>> REFERENCES
            = new ConcurrentHashMap<>();
    
    static Node<JsonObject> infix(final String key) {
        return Fn.pool(ZeroInfix.REFERENCES, key, () -> new ZeroInfix(key));
    }
}
