package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;

import java.lang.reflect.Method;

/**
 * The middle point for method
 */
public class NodeTransit implements Transit {

    @Override
    public Future<Envelop> async(final Envelop data) {
        return null;
    }

    @Override
    public Transit connect(final Method method) {
        return null;
    }
}
