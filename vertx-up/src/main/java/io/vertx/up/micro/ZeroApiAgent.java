package io.vertx.up.micro;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.up.annotations.Agent;
import io.vertx.up.eon.Orders;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.router.RouterAxis;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.config.ServerVisitor;
import io.vertx.zero.eon.Values;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.micro.config.CircuitVisitor;
import io.vertx.zero.micro.config.DynamicVisitor;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Api Gateway for micro service architecture,
 * 1. Enable Service Discovery to search EndPoints
 * 2. Enable CircutBreaker to do breaker.
 */
@Agent(type = ServerType.API)
public class ZeroApiAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroApiAgent.class);

    private static final ServerVisitor<HttpServerOptions> VISITOR =
            Instance.singleton(DynamicVisitor.class);

    private static final Visitor<CircuitBreakerOptions> CIRCUIT_VISITOR =
            Instance.singleton(CircuitVisitor.class);
    private static CircuitBreakerOptions OPTIONS;
    private static final ConcurrentMap<Integer, AtomicInteger>
            API_START_LOGS = new ConcurrentHashMap<>();

    static {
        Fn.safeZero(() -> {
            if (ZeroAtomic.API_OPTS.isEmpty()) {
                ZeroAtomic.API_OPTS.putAll(VISITOR.visit(ServerType.API.toString()));
                ZeroAtomic.API_OPTS.forEach((port, option) -> {
                    API_START_LOGS.put(port, new AtomicInteger(0));
                });
            }
            if (null == OPTIONS) {
                OPTIONS = CIRCUIT_VISITOR.visit();
            }
        }, LOGGER);
    }

    @Override
    public void start() {
        /** 1.Call router hub to mount commont **/
        final Axis<Router> routerAxiser = Fn.poolThread(Pool.ROUTERS,
                () -> Instance.instance(RouterAxis.class));

        Fn.safeZero(() -> {

            // Set breaker for each server
            ZeroAtomic.API_OPTS.forEach((port, option) -> {
                /** Single server processing **/
                final HttpServer server = this.vertx.createHttpServer(option);
                /** Router **/
                final Router router = Router.router(this.vertx);
                routerAxiser.mount(router);

                /** Breaker and Dispatch **/
                router.route("/*")
                        .order(Orders.EVENT)
                        .handler(this.dispatchRequests(option));

                /** Listening **/
                server.requestHandler(router::accept).listen();
                {
                    registryServer(option);
                }
            });
        }, LOGGER);
    }

    private Handler<RoutingContext> dispatchRequests(final HttpServerOptions options) {
        final String name = options.getHost() + options.getPort();
        final CircuitBreaker breaker = CircuitBreaker.create(name, this.vertx, OPTIONS);
        final ServiceDiscovery discovery = ServiceDiscovery.create(this.vertx);
        return context -> {
            // Run with circuit breaker
            breaker.execute(future -> getEndPoints(discovery).setHandler(res -> {
                if (res.succeeded()) {
                    final List<Record> records = res.result();
                    // Find the record.
                    records.forEach(item -> {
                        System.out.println(item.getLocation());
                    });
                } else {

                }
            }));
        };
    }

    private Future<List<Record>> getEndPoints(final ServiceDiscovery discovery) {
        final Future<List<Record>> future = Future.future();
        discovery.getRecords(record -> record.getType().equals(HttpEndpoint.TYPE),
                future.completer());
        return future;
    }

    private void registryServer(final HttpServerOptions options) {
        final Integer port = options.getPort();
        final AtomicInteger out = API_START_LOGS.get(port);
        if (Values.ZERO == out.getAndIncrement()) {
            final String portLiteral = String.valueOf(port);
            LOGGER.info(Info.API_GATEWAY, getClass().getSimpleName(), deploymentID(),
                    portLiteral);
            final String address =
                    MessageFormat.format("http://{0}:{1}/",
                            options.getHost(), portLiteral);
            LOGGER.info(Info.API_LISTEN, getClass().getSimpleName(), address);
        }
    }
}
