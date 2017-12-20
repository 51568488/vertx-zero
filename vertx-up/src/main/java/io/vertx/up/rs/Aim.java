package io.vertx.up.rs;

import io.vertx.core.Handler;
import io.vertx.up.atom.agent.Event;

/**
 * Hunt to aim and select the objective
 */
public interface Aim<Context> {
    /**
     * @param event
     * @return
     */
    Handler<Context> attack(final Event event);
}
