package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Aim;

public class AsyncAim implements Aim {
    @Override
    public Handler<RoutingContext> attack(final Event event) {

        return null;
    }
}
