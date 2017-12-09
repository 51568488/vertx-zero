package io.vertx.up.kidd;

import io.vertx.core.AsyncResult;
import io.vertx.up.exception.WebException;

/**
 * Outcome for response definition
 */
public interface Obstain<T> {
    /**
     * 1. Success
     *
     * @param handler
     * @return
     */
    void response(final AsyncResult<T> handler,
                  final Spy<T> spy) throws WebException;
}
