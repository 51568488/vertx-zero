package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;

/**
 * The last point for method
 */
public class FinalTransit implements Transit {

    @Override
    public Future<Envelop> async(final Envelop data) {
        return null;
    }
}
