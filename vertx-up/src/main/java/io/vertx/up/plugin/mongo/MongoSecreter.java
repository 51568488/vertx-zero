package io.vertx.up.plugin.mongo;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.mongo.impl.MongoAuthImpl;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.func.Fn;
import io.vertx.up.secure.Secreter;
import io.vertx.up.tool.mirror.Instance;

/**
 * Mongo Security Components.
 */
@SuppressWarnings("unchecked")
public class MongoSecreter
        implements Secreter<RoutingContext, Vertx> {

    @Override
    public AuthHandler mount(final Cliff cliff,
                             final Vertx vertx) {
        final AuthProvider provider = getProvider(cliff);
        final AuthHandler handler = Fn.getJvm(() -> Instance.instance(cliff.getHandler(), provider), provider);
        return handler;
    }

    private AuthProvider getProvider(final Cliff cliff) {
        // 1. Get client
        final MongoClient client = MongoInfix.getClient();
        return Fn.getJvm(() -> {
            // Config
            final JsonObject config = cliff.getConfig();
            // Create provider instance.
            final AuthProvider provider1 = new MongoAuthImpl(client, config);
            System.out.println(provider1);
            final AuthProvider provider = Instance.instance(cliff.getProvider(), client, config);
            return Instance.instance(cliff.getProvider(), client, config);
        }, client);
    }
}
