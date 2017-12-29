package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.annotations.Agent;
import io.vertx.up.eon.ID;
import io.vertx.up.eon.Micro;
import io.vertx.up.eon.em.Etat;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.center.ZeroRegistry;
import io.vertx.up.rs.Axis;
import io.vertx.up.rs.router.EventAxis;
import io.vertx.up.rs.router.RouterAxis;
import io.vertx.up.tool.Net;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroGrid;
import io.vertx.zero.eon.Values;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Default Http Server agent for router handlers.
 * Once you have defined another Agent, the default agent will be replaced.
 * Recommend: Do not modify any agent that vertx zero provided.
 */
@Agent
public class ZeroHttpAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroHttpAgent.class);

    private static final ConcurrentMap<Integer, String> SERVICES =
            ZeroGrid.getServerNames();

    @Override
    public void start() {
        /** 1.Call router hub to mount commont **/
        final Axis<Router> routerAxiser = Fn.poolThread(Pool.ROUTERS,
                () -> Instance.instance(RouterAxis.class));
        /** 2.Call route hub to mount defined **/
        final Axis<Router> axiser = Fn.poolThread(Pool.EVENTS,
                () -> Instance.instance(EventAxis.class));

        /** 3.Get the default HttpServer Options **/
        ZeroAtomic.HTTP_OPTS.forEach((port, option) -> {
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
                registryServer(option, router);
            }
        });
    }

    @Override
    public void stop() {
        Fn.itMap(ZeroAtomic.HTTP_OPTS, (port, config) -> {
            final AtomicInteger out = ZeroAtomic.HTTP_STOP_LOGS.get(port);
            if (Values.ONE == out.getAndIncrement()) {
                // Status registry
                endRegistry(SERVICES.get(port), config);
            }
        });
    }

    private void registryServer(final HttpServerOptions options,
                                final Router router) {
        final Integer port = options.getPort();
        final AtomicInteger out = ZeroAtomic.HTTP_START_LOGS.get(port);
        if (Values.ZERO == out.getAndIncrement()) {
            // 1. Build logs for current server;
            final String portLiteral = String.valueOf(port);
            LOGGER.info(Info.HTTP_SERVERS, getClass().getSimpleName(), deploymentID(),
                    portLiteral);
            final List<Route> routes = router.getRoutes();
            final Map<String, Route> routeMap = new TreeMap<>();

            final JsonObject tree = new JsonObject();
            for (final Route route : routes) {
                // 2.Route
                final String path = null == route.getPath() ? "/*" : route.getPath();
                routeMap.put(path, route);
                if (!"/*".equals(path)) {
                    // 3.Regex
                    final String regex = buildRegex(route);
                    tree.put(path, regex);
                }
            }
            routeMap.forEach((path, route) ->
                    LOGGER.info(Info.MAPPED_ROUTE, getClass().getSimpleName(), path,
                            route.toString()));
            // 3. Endpoint Publish
            final String address =
                    MessageFormat.format("http://{0}:{1}/",
                            Net.getIPv4(), portLiteral);
            LOGGER.info(Info.HTTP_LISTEN, getClass().getSimpleName(), address);
            // 4. Send configuration to Event bus
            final String name = SERVICES.get(port);
            startRegistry(name, options, tree);
        }
    }

    private String buildRegex(final Route name) {

        return name.getPath();
    }

    private void endRegistry(final String name,
                             final HttpServerOptions options) {
        // Enabled micro mode.
        if (EtcdData.enabled()) {
            // Template call registry to modify the status of current service.
            final ZeroRegistry registry = ZeroRegistry.create(getClass());
            registry.registryHttp(name, options, Etat.STOPPED);
        }
    }

    private void startRegistry(final String name,
                               final HttpServerOptions options,
                               final JsonObject tree) {
        // Enabled micro mode.
        if (EtcdData.enabled()) {
            final JsonObject data = getMessage(name, options, tree);
            // Send Data to Event Bus
            this.sendMessage(name, ID.Addr.REGISTRY_START, data);
        }
    }

    private void sendMessage(final String name,
                             final String address,
                             final JsonObject data) {
        final EventBus bus = this.vertx.eventBus();
        LOGGER.info(Info.MICRO_REGISTRY_SEND, getClass().getSimpleName(), name, address);
        bus.publish(address, data);
    }

    private JsonObject getMessage(final String name,
                                  final HttpServerOptions options,
                                  final JsonObject tree) {
        final JsonObject data = new JsonObject();
        data.put(Micro.Registry.NAME, name);
        data.put(Micro.Registry.OPTIONS, options.toJson());
        // No Uri
        if (null != tree) {
            data.put(Micro.Registry.URIS, tree);
        }
        return data;
    }
}
