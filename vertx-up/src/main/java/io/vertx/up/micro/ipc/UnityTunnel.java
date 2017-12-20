package io.vertx.up.micro.ipc;

import io.vertx.core.Future;
import io.vertx.up.ipc.eon.IpcRequest;
import io.vertx.up.ipc.eon.IpcResponse;
import io.vertx.up.ipc.service.UnityServiceGrpc;

/**
 * Unity tunnel
 */
public class UnityTunnel extends
        UnityServiceGrpc.UnityServiceVertxImplBase {

    @Override
    public void unityCall(final IpcRequest request, final Future<IpcResponse> future) {
        
    }
}
