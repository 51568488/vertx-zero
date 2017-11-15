package io.vertx.up.rs.argument;

import io.vertx.core.MultiMap;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;
import io.vertx.up.web.ZeroSerializer;

/**
 * Parse Query Params
 *
 * @QueryParam
 */
public class QueryFiller implements Filler {

    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        // 1. Get query information.
        final MultiMap map = context.queryParams();
        // 2. Get name
        return ZeroSerializer.getValue(paramType, map.get(name));
    }
}
