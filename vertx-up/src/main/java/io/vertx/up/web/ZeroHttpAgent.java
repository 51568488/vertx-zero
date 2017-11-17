package io.vertx.up.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.up.annotations.Agent;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.router.EventAxis;
import io.vertx.up.rs.router.RouterAxis;
import io.vertx.zero.eon.Values;
import io.vertx.zero.func.HPool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import static io.vertx.up.web.Pool.EVENTS;
import static io.vertx.up.web.Pool.ROUTERS;

/**
 * Default Http Server agent for router handlers.
 * Once you have defined another Agent, the default agent will be replaced.
 * Recommend: Do not modify any agent that vertx zero provided.
 */
@Agent
public class ZeroHttpAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroHttpAgent.class);
    /**
     * Extract all http server options.
     */
    private static final ConcurrentMap<Integer, HttpServerOptions>
            SERVERS = ZeroGrid.getServerOptions();
    private static final ConcurrentMap<Integer, AtomicInteger>
            LOGS = new ConcurrentHashMap<Integer, AtomicInteger>() {
        {
            SERVERS.forEach((port, option) -> {
                put(port, new AtomicInteger(0));
            });
        }
    };

    private transient final String NAME = getClass().getSimpleName();

    @Override
    public void start() {
        /** 1.Call router hub to mount commont **/
        final Axis routerAxiser = HPool.exec(ROUTERS, Thread.currentThread().getName(),
                () -> Instance.instance(RouterAxis.class));
        /** 2.Call route hub to mount defined **/
        final Axis axiser = HPool.exec(EVENTS, Thread.currentThread().getName(),
                () -> Instance.instance(EventAxis.class));

        /** 3.Get the default HttpServer Options **/
        SERVERS.forEach((port, option) -> {
            /** 3.1.Single server processing **/
            final HttpServer server = this.vertx.createHttpServer(option);
            
            /** 3.2. Build router with current option **/
            final Router router = Router.router(this.vertx);

            routerAxiser.mount(router);
            axiser.mount(router);

            /** 3.3.Listen for router on the server **/
            server.requestHandler(router::accept).listen();
            {
                // 3.4. Log output
                recordServer(option, router);
            }
        });
    }

    private void recordServer(final HttpServerOptions options,
                              final Router router) {
        final Integer port = options.getPort();
        final AtomicInteger out = LOGS.get(port);
        if (Values.ZERO == out.getAndIncrement()) {
            // 1. Build logs for current server;
            final String portLiteral = String.valueOf(port);
            LOGGER.info(Info.HTTP_SERVERS, this.NAME, deploymentID(),
                    portLiteral);
            final List<Route> routes = router.getRoutes();
            for (final Route route : routes) {
                // 2.Route
                final String path = null == route.getPath() ? "/*" : route.getPath();
                LOGGER.info(Info.MAPPED_ROUTE, this.NAME, path,
                        route.toString());
            }
            // 3. Endpoint Publish
            final String address =
                    MessageFormat.format("http://{0}:{1}/",
                            options.getHost(), portLiteral);
            LOGGER.info(Info.HTTP_LISTEN, this.NAME, address);
        }
    }
}
