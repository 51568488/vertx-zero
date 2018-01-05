package io.vertx.up.micro.ipc.server;

import io.grpc.BindableService;
import io.vertx.core.Future;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.eon.IpcResponse;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.eon.em.IpcType;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ipc.DataEncap;

import java.lang.reflect.Method;

/**
 * Unity tunnel
 */
public class UnityTunnel implements Tunnel {

    private static final Annal LOGGER = Annal.get(UnityTunnel.class);

    @Override
    public BindableService init() {
        return new UnityServiceGrpc.UnityServiceVertxImplBase() {
            @Override
            public void unityCall(final IpcRequest request, final Future<IpcResponse> future) {
                LOGGER.info("Hello");
                // IpcData building
                final IpcData data = DataEncap.consume(request, IpcType.UNITY);
                // Method handle
                final Method method = IPCS.get(data.getAddress());
                System.out.println(data.getAddress());
                if (null != method) {
                    // Method called with message handler
                    final Envelop envelop = DataEncap.consume(data);

                    System.out.println(method);
                    System.out.println(envelop);
                } else {
                    // TODO: Error internal Rpc
                }
            }
        };
    }
}
