package io.vertx.up.rs;

import io.vertx.core.Handler;
import io.vertx.up.atom.agent.Depot;

/**
 * JSR330 signal
 */
public interface Sentry<Context> {
    /**
     * @param depot
     * @return
     */
    Handler<Context> signal(final Depot depot);
}
