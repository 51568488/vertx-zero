package io.vertx.up.rs.argument;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;

public class HeaderFiller implements Filler {
    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        // Extract data from header
        final HttpServerRequest request = context.request();
        return Value.get(request.getHeader(name), paramType);
    }
}
