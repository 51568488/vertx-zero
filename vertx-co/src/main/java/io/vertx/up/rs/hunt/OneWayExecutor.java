package io.vertx.up.rs.hunt;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;

/**
 * One-way message ( Reference from WSDL definition )
 * Mode:  Client -> ( Message ) -> Server
 * No response needed, only need to know the status: Success | Failue
 * One way specification
 * 1. Method return type must be Void
 */
public class OneWayExecutor implements Executor {

    @Override
    public void execute(final RoutingContext context,
                        final Event event) {

    }
}
