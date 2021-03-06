package io.vertx.up.micro.ipc.server;

import io.grpc.BindableService;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.tp.ipc.eon.IpcRequest;
import io.vertx.tp.ipc.eon.IpcResponse;
import io.vertx.tp.ipc.service.UnityServiceGrpc;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.eon.em.IpcType;
import io.vertx.up.exception._501RpcMethodMissingException;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ipc.DataEncap;
import io.vertx.up.micro.ipc.tower.FinalTransit;
import io.vertx.up.micro.ipc.tower.NodeTransit;
import io.vertx.up.micro.ipc.tower.Transit;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Instance;

import java.lang.annotation.Annotation;
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
                // Method called with message handler
                final Envelop envelop = DataEncap.consume(data);
                // Method handle
                final Method method = IPCS.get(data.getAddress());
                // Work mode
                if (null == method) {
                    // No Rpc Handler here
                    final Envelop community = Envelop.failure(
                            new _501RpcMethodMissingException(this.getClass(), data.getAddress()));
                    // Build IpcData
                    final IpcData responseData = UnityTunnel.this.build(community, envelop);
                    future.complete(DataEncap.out(responseData));
                } else {
                    // Execute Transit
                    final Transit transit = UnityTunnel.this.getTransit(method);
                    // Execute Transit
                    final Future<Envelop> result = transit.async(envelop);
                    result.setHandler(res -> {
                        final IpcData responseData = UnityTunnel.this.build(res.result(), envelop);
                        future.complete(DataEncap.out(responseData));
                    });
                }
            }
        };
    }

    private IpcData build(final Envelop community, final Envelop envelop) {
        // Headers and user could not be modified
        community.setHeaders(envelop.headers());
        community.setUser(envelop.user());
        // IpcResponse -> Output Envelop
        final IpcData responseData = new IpcData();
        responseData.setType(IpcType.UNITY);
        DataEncap.in(responseData, community);
        return responseData;
    }

    private Transit getTransit(final Method method) {
        final Annotation annotation = method.getAnnotation(Ipc.class);
        // 1. Check only one is enough because of Error-40043
        // 2. to and from must not be null at the same time because of Error-40045
        final String to = Instance.invoke(annotation, "to");
        final Transit transit;
        if (StringUtil.isNil(to)) {
            // Node transit
            transit = Instance.singleton(FinalTransit.class);
            LOGGER.info(Info.NODE_FINAL, method, method.getDeclaringClass());
        } else {
            // Final transit
            transit = Instance.singleton(NodeTransit.class);
            LOGGER.info(Info.NODE_MIDDLE, method, method.getDeclaringClass());
        }
        // Connect for transit
        transit.connect(method);
        return transit;
    }
}
