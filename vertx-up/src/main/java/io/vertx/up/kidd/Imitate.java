package io.vertx.up.kidd;

import io.vertx.core.eventbus.Message;
import io.vertx.up.atom.Envelop;

/**
 * Income for request definition
 */
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
