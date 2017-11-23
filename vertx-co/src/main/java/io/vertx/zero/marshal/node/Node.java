package io.vertx.zero.marshal.node;

import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;

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
    static Node<JsonObject> infix(final String key) {
        return Fn.pool(ZeroInfix.REFERENCES, key, () -> new ZeroInfix(key));
    }
}
