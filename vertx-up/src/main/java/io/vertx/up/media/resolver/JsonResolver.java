package io.vertx.up.media.resolver;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.exception.WebException;
import io.vertx.up.media.Resolver;

public class JsonResolver<T> implements Resolver<T> {

    @Override
    public Epsilon<T> resolve(final RoutingContext context,
                              final Epsilon<T> income)
            throws WebException {
        // Json Resolver
        System.out.println(income);
        return income;
    }
}
