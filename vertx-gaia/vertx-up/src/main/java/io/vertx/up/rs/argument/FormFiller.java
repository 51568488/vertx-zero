package io.vertx.up.rs.argument;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Filler;
import io.vertx.up.web.ZeroSerializer;

public class FormFiller implements Filler {

    @Override
    public Object apply(final String name,
                        final Class<?> paramType,
                        final RoutingContext context) {
        final String value = context.request().getFormAttribute(name);
        return ZeroSerializer.getValue(paramType, value);
    }
}
