package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;

/**
 * Different workflow for method call
 */
public interface Transit {
    /**
     * Async mode
     *
     * @return
     */
    Future<Envelop> async(Envelop data);
}
