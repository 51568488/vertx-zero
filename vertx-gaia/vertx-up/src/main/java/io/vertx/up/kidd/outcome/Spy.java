package io.vertx.up.kidd.outcome;

import io.vertx.up.atom.Envelop;

/**
 * Id implementation for identifier to front
 */
public interface Spy {
    /**
     * Envelop Processing
     *
     * @param request
     * @return
     */
    Envelop to(Envelop request);
}
