package io.vertx.up.kidd.income;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

/**
 * Income for request definition
 */

@Deprecated
public interface Imitate<T> {
    /**
     * @param message
     * @return
     */
    T request(Message<Envelop> message);

    /**
     * @param message
     * @param index
     * @return
     */
    T request(Message<Envelop> message, int index);
}
