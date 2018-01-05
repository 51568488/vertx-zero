package io.vertx.up.micro.ipc.server;

import io.grpc.BindableService;

/**
 * Rpc Service
 */
public interface Tunnel {
    /**
     * Create new Rpc Service by type
     *
     * @return
     */
    BindableService init();
}
