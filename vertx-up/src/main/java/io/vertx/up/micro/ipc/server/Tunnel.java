package io.vertx.up.micro.ipc.server;

import io.grpc.BindableService;
import io.vertx.up.web.ZeroAnno;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;

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

    ConcurrentMap<String, Method> IPCS
            = ZeroAnno.getIpcs();
}
