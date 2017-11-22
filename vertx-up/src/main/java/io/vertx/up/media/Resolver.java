package io.vertx.up.media;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.exception.WebException;

public interface Resolver<T> {

    Epsilon<T> resolve(RoutingContext context,
                       Epsilon<T> income)
            throws WebException;
}
