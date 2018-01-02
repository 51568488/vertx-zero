package io.vertx.up.secure;

import io.vertx.core.Handler;
import io.vertx.up.atom.secure.Cliff;

/**
 * Secure Handler building for Zero system.
 * 1. Whether the system enable Auth
 * 2. Create ChainHandler for sequence auth
 * 3. Secure path configuration.
 */
public interface Secreter<C, V> {
    /**
     * Build ChainHandler based on Guard
     *
     * @return
     */
    <A extends Handler<C>> A mount(Cliff cliff, V vertxRef);
}
