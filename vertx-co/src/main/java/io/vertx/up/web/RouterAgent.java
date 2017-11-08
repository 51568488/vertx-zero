package io.vertx.up.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Default agent for routing, this agent will be always deployed
 */
public class RouterAgent extends AbstractVerticle {

    private final transient ConcurrentMap<Integer, HttpServerOptions>
            options = new ConcurrentHashMap<>();

    @Override
    public void start() {
        final HttpServer server = this.vertx.createHttpServer();
        final Router router = Router.router(this.vertx);
        router.get("/test").handler((context) -> {
            System.out.println("Hello");
            context.response().end("Hello World");
        });
        server.requestHandler(router::accept).listen(7064);
    }
}
