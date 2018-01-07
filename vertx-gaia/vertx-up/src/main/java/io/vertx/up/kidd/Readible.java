package io.vertx.up.kidd;

import io.vertx.up.exception.WebException;
import io.vertx.up.kidd.outcome.CodeReadible;
import io.vertx.up.tool.mirror.Instance;

/**
 * Fill the field "readible" for input exception
 */
public interface Readible {
    /**
     * Fill the field "readible" for web exception
     *
     * @param error
     * @return
     */
    void interpret(WebException error);

    /**
     * Get code readible
     *
     * @return
     */
    static Readible get() {
        return Instance.singleton(CodeReadible.class);
    }
}
