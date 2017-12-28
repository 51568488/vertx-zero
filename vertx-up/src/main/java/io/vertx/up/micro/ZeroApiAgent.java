package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.up.annotations.Agent;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.micro.DynamicVistor;
import io.vertx.zero.marshal.micro.ServerVisitor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Agent(type = ServerType.API)
public class ZeroApiAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroApiAgent.class);

    private static final ServerVisitor<HttpServerOptions> VISITOR =
            Instance.singleton(DynamicVistor.class);
    private static final ConcurrentMap<Integer, HttpServerOptions> API_OPTS =
            new ConcurrentHashMap<>();

    static {
        Fn.safeZero(() -> {
            if (API_OPTS.isEmpty()) {
                API_OPTS.putAll(VISITOR.visit(ServerType.API.toString()));
            }
        }, LOGGER);
    }

    @Override
    public void start() {
        System.out.println("Api");
        System.out.println(API_OPTS);
    }
}
