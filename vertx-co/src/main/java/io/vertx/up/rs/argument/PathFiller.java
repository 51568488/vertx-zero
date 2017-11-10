package io.vertx.up.rs.argument;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;

public class PathFiller implements Filler {
    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        // 1. Get path information
        return Value.get(context.pathParam(name), paramType);
    }
}
