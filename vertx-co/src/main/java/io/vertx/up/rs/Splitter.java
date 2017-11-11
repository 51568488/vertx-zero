package io.vertx.up.rs;

import io.vertx.up.ce.Event;

/**
 * Splitter to get executor reference.
 * It will happen in startup of route building to avoid
 * request resource spending.
 * 1. Level 1: Distinguish whether enable EventBus
 * EventBus mode: Async
 * Non-EventBus mode: Sync
 * 2. Level 2: Distinguish the request mode
 * One-Way mode: No response needed. ( Return Type )
 * Request-Response mode: Must require response. ( Return Type )
 */
public interface Splitter {
    /**
     * Event distribute
     *
     * @param event
     * @return
     */
    Aim distribute(final Event event);
}
