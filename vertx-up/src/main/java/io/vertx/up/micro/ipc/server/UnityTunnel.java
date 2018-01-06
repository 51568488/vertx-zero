package io.vertx.up.micro.ipc.server;

import io.grpc.BindableService;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.eon.IpcResponse;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.eon.em.IpcType;
import io.vertx.up.exception._501RpcMethodMissingException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ipc.DataEncap;
import io.vertx.up.tool.mirror.Instance;

import java.lang.reflect.Method;

/**
 * Unity tunnel
 */
public class UnityTunnel implements Tunnel {

    private static final Annal LOGGER = Annal.get(UnityTunnel.class);

    @Override
    public BindableService init(final Vertx vertx) {
        return new UnityServiceGrpc.UnityServiceVertxImplBase() {
            @Override
            public void unityCall(final IpcRequest request, final Future<IpcResponse> future) {
                // IpcData building
                final IpcData data = DataEncap.consume(request, IpcType.UNITY);
                // Method handle
                final Method method = IPCS.get(data.getAddress());
                // Work mode
                if (null == method) {
                    // No Rpc Handler here
                    final Envelop envelop =
                            Envelop.failure(new _501RpcMethodMissingException(getClass(), data.getAddress()));
                } else {
                    // Method called with message handler
                    final Envelop envelop = DataEncap.consume(data);
                    // Send message to address
                    final Class<?> returnType = method.getReturnType();
                    // Method dispatch for different workflow.
                    if (Void.class == returnType || void.class == returnType) {
                        // Message sending
                        final EventBus bus = vertx.eventBus();
                        bus.<JsonObject>send(data.getAddress(), envelop, message -> {
                            if (message.succeeded()) {
                                // Replied
                                System.out.println("Hello" + message.result().body());
                            } else {

                            }
                        });
                    } else if (Envelop.class == returnType) {
                        // Sync mode
                        final Object proxy = Instance.singleton(method.getDeclaringClass());
                        final Envelop result = Fn.getJvm(() ->
                                buildResponse(method.invoke(proxy, envelop)), LOGGER);
                    } else {
                        // No Rpc Handler here. Error
                    }
                }
            }
        };
    }

    private Envelop buildResponse(final Object ret) {
        // Return value
        if (null == ret) {
            // Null converted to Envelop
            return Envelop.ok();
        } else {
            if (ret instanceof Envelop) {
                // Direct return
                return (Envelop) ret;
            } else {
                // Success for other types
                return Envelop.success(ret);
            }
        }
    }
}
