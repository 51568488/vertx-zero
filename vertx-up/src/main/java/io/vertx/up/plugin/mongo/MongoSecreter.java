package io.vertx.up.plugin.mongo;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BasicAuthHandler;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.secure.Secreter;

/**
 * Mongo Security Components.
 */
public class MongoSecreter
        implements Secreter<BasicAuthHandler, RoutingContext> {

    @Override
    public BasicAuthHandler mount(final Cliff cliff) {

        return null;
    }
}
