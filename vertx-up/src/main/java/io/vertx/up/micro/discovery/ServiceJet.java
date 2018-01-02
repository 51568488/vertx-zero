package io.vertx.up.micro.discovery;

import io.vertx.circuitbreaker.CircuitBreaker;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._404ServiceNotFoundException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.matcher.Arithmetic;
import io.vertx.up.micro.matcher.CommonArithmetic;
import io.vertx.up.rs.hunt.Answer;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.Visitor;
import io.vertx.zero.micro.config.CircuitVisitor;

import java.util.List;

public class ServiceJet {
    private static final Annal LOGGER = Annal.get(ServiceJet.class);
    private static final Visitor<CircuitBreakerOptions> VISITOR =
            Instance.singleton(CircuitVisitor.class);
    private static CircuitBreakerOptions OPTIONS;

    static {
        Fn.safeZero(() -> {
            if (null == OPTIONS) {
                OPTIONS = VISITOR.visit();
            }
        }, LOGGER);
    }

    private transient ServiceDiscovery discovery;
    private transient CircuitBreaker breaker;
    private final transient Arithmetic arithmetic = Instance.singleton(CommonArithmetic.class);
    private final transient HttpServerOptions options;

    public static ServiceJet create(final HttpServerOptions options) {
        return new ServiceJet(options);
    }

    private ServiceJet(final HttpServerOptions options) {
        this.options = options;
    }

    public ServiceJet connect(final Vertx vertx) {
        this.discovery = ServiceDiscovery.create(vertx);
        final String name = this.options.getHost() + this.options.getPort();
        this.breaker = CircuitBreaker.create(name, vertx, OPTIONS);
        return this;
    }

    public Handler<RoutingContext> handle() {
        return context -> {
            // Run with circuit breaker
            this.breaker.execute(future -> getEndPoints().setHandler(res -> {
                if (res.succeeded()) {
                    final List<Record> records = res.result();
                    // Find the record hitted. ( Include Path variable such as /xx/yy/:zz/:xy )
                    final Record hitted = this.arithmetic.search(records, context);
                    // Complete actions.
                    if (null == hitted) {
                        // Service Not Found ( 404 )
                        replyError(context);
                    } else {
                        // Find record, dispatch request
                        System.out.println(hitted.toJson());
                    }
                    future.complete();
                } else {
                    // Future failed
                    future.fail(res.cause());
                }
            }));
        };
    }

    /**
     * Service Not Found ( 404 )
     *
     * @param context
     */
    private void replyError(final RoutingContext context) {
        final HttpServerRequest request = context.request();
        final WebException exception = new _404ServiceNotFoundException(getClass(), request.uri(),
                request.method());
        Answer.reply(context, Envelop.failure(exception));
    }

    private Future<List<Record>> getEndPoints() {
        final Future<List<Record>> future = Future.future();
        this.discovery.getRecords(record -> record.getType().equals(HttpEndpoint.TYPE),
                future.completer());
        return future;
    }
}
