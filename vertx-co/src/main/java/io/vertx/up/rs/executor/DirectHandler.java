package io.vertx.up.rs.executor;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Executor;

import java.util.ArrayList;
import java.util.List;

/**
 * Non-Event Bus Mode
 * Request workflow：
 * 1. Pre process request by container
 * 2. Scan event action about parameters
 * 3. Execute event action
 * 4. Get response data and send response directly.
 */
public class DirectHandler implements Executor {

    @Override
    public void execute(final RoutingContext context,
                        final Event event) {
        // 1. Call action
        final List<Object> args = new ArrayList<>();
        ArgsFiller.process(args, context, event);
    }
}
