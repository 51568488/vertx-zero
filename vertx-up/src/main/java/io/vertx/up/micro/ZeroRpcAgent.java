package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.ServidorOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.grpc.VertxServer;
import io.vertx.grpc.VertxServerBuilder;
import io.vertx.up.annotations.Agent;
import io.vertx.up.eon.ID;
import io.vertx.up.eon.em.CertType;
import io.vertx.up.eon.em.Etat;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.center.ZeroRegistry;
import io.vertx.up.micro.ipc.server.Tunnel;
import io.vertx.up.micro.ipc.server.UnityTunnel;
import io.vertx.up.micro.ssl.CertPipe;
import io.vertx.up.tool.Net;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Values;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Internal Rpc Server, called IPC
 * Once you have defined another Agent, the default will be replaced.
 */
@Agent(type = ServerType.IPC)
public class ZeroRpcAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroRpcAgent.class);
    private static final String SSL = "ssl";

    private final transient ZeroRegistry registry
            = ZeroRegistry.create(getClass());

    @Override
    public void start() {
        /** 1. Iterate all the configuration **/
        Fn.itMap(ZeroAtomic.RPC_OPTS, (port, config) -> {
            /** 2.Rcp server builder initialized **/
            final VertxServerBuilder builder = VertxServerBuilder
                    .forAddress(this.vertx, config.getHost(), config.getPort());
            /**
             * 3.Must contains following config item:
             * ssl, alpn, jks, password
             * **/
            final JsonObject options = config.getOptions();
            // 4.SSL Enabled
            if (options.containsKey(SSL) && Boolean.valueOf(options.getValue(SSL).toString())) {
                final Object type = options.getValue("type");
                final CertType certType = null == type ?
                        CertType.PEM : Types.fromStr(CertType.class, type.toString());
                final CertPipe<JsonObject> pipe = CertPipe.get(certType);
                builder.useSsl(pipe.parse(options));
            }
            /**
             * 5.Service added.
             */
            {
                // UnityService add
                final Tunnel tunnel = Instance.singleton(UnityTunnel.class);
                builder.addService(tunnel.init());
            }
            /**
             * 6.Server added.
             */
            final VertxServer server = builder.build();
            server.start(handler -> registryServer(handler, config));
        });
    }

    @Override
    public void stop() {
        Fn.itMap(ZeroAtomic.RPC_OPTS, (port, config) -> {
            // Status registry
            this.registry.registryRpc(config, Etat.STOPPED);
        });
    }

    /**
     * Registry the data into etcd
     *
     * @param handler
     * @param options
     */
    private void registryServer(final AsyncResult<Void> handler,
                                final ServidorOptions options) {
        final Integer port = options.getPort();
        final AtomicInteger out = ZeroAtomic.RPC_START_LOGS.get(port);
        if (Values.ONE == out.getAndIncrement()) {
            if (handler.succeeded()) {
                LOGGER.info(Info.RPC_LISTEN, Net.getIPv4(), String.valueOf(options.getPort()));
                // Started to write data in etcd center.
                LOGGER.info(Info.ETCD_SUCCESS, this.registry.getConfig());
                // Status registry
                startRegistry(options);
            } else {
                LOGGER.info(Info.RPC_FAILURE, null == handler.cause() ? "None" : handler.cause().getMessage());
            }
        }
    }

    private void startRegistry(final ServidorOptions options) {
        // Rpc Agent is only valid in Micro mode
        final EventBus bus = this.vertx.eventBus();
        final String address = ID.Addr.IPC_START;
        LOGGER.info(Info.IPC_REGISTRY_SEND, getClass().getSimpleName(), options.getName(), address);
        bus.publish(address, options.toJson());
    }
}
