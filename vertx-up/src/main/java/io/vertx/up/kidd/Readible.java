package io.vertx.up.kidd;

import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;

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
    Envelop interpret(WebException error);
}
