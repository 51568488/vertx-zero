package io.vertx.up.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.up.annotations.Agent;
import org.vie.util.log.Annal;

import java.util.concurrent.ConcurrentMap;

/**
 * Default Http Server agent for router handlers.
 * Once you have defined another Agent, the default agent will be replaced.
 * Recommend: Do not modify any agent that vertx zero provided.
 */
@Agent
public class HttpAgent extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(HttpAgent.class);
    /**
     * Extract all http server options.
     */
    private static final ConcurrentMap<Integer, HttpServerOptions>
            SERVERS = ZeroGrid.getServerOptions();

    @Override
    public void start() {
        /** 1.Get the default HttpServer Options **/
        final Router router = Router.router(this.vertx);
        final Route route = router.route();
    }
}
