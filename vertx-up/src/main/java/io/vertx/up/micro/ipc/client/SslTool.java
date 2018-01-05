package io.vertx.up.micro.ipc.client;

import io.grpc.ManagedChannel;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.grpc.VertxChannelBuilder;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

public class SslTool {

    private static final Annal LOGGER = Annal.get(SslTool.class);

    private static final Node<JsonObject> node = Instance.singleton(ZeroUniform.class);

    public static ManagedChannel getChannel(final Vertx vertx,
                                            final IpcData data) {
        final String grpcHost = data.getHost();
        final Integer grpcPort = data.getPort();
        LOGGER.info(Info.CLIENT_BUILD, grpcHost, String.valueOf(grpcPort));
        final VertxChannelBuilder builder =
                VertxChannelBuilder
                        .forAddress(vertx, grpcHost, grpcPort);
        // Ssl Required
        final JsonObject config = node.read();

        Fn.safeSemi(null != config && null != config.getValue("rpc"), LOGGER,
                () -> {
                    // Extension or Uniform
                    final JsonObject rpcConfig = config.getJsonObject("rpc");

                    final JsonObject sslConfig = new JsonObject();
                    if (rpcConfig.containsKey("extension")) {
                        // Non Uniform, Search by name
                        final String name = data.getName();
                        final JsonObject visited = Jackson.visitJObject(rpcConfig, "extension", name);
                        if (null != visited) {
                            sslConfig.mergeIn(visited);
                        }
                    }
                    if (sslConfig.isEmpty()) {
                        // Uniform mode default.
                        sslConfig.mergeIn(Jackson.visitJObject(rpcConfig, "uniform"));
                    }
                    // Set builder
                    final String jks = sslConfig.getString("jks");
                    final String password = sslConfig.getString("password");
                    LOGGER.info(Info.CLIENT_SSL, jks, null != password);
                    builder.useSsl(option -> option
                            .setSsl(true)
                            .setUseAlpn(true)
                            .setTrustStoreOptions(new JksOptions()
                                    .setPath(jks)
                                    .setPassword(password)));
                });
        return builder.build();
    }
}
