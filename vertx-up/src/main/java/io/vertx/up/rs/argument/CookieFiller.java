package io.vertx.up.rs.argument;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;

public class CookieFiller implements Filler {
    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        // TODO: Cookies
        return null;
    }
}
