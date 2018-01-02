package io.vertx.up.secure;

import io.vertx.core.Handler;

/**
 * Secure Handler building for Zero system.
 * 1. Whether the system enable Auth
 * 2. Create ChainHandler for sequence auth
 * 3. Secure path configuration.
 */
public interface Secreter<AuthHandler extends Handler<Context>, Context> {
    /**
     * Build ChainHandler based on Guard
     *
     * @return
     */
    AuthHandler mount();
}
