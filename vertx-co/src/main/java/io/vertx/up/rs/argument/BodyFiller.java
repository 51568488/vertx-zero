package io.vertx.up.rs.argument;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;
import io.vertx.zero.web.ZeroSerializer;

public class BodyFiller implements Filler {
    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        return ZeroSerializer.getValue(paramType, context.getBodyAsString());
    }
}
