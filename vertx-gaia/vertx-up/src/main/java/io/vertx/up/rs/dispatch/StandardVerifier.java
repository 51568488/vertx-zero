package io.vertx.up.rs.dispatch;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Rule;
import io.vertx.up.atom.agent.Depot;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.hunt.BaseAim;

import java.util.List;
import java.util.Map;

/**
 * Major execution to verify the result.
 */
public class StandardVerifier extends BaseAim implements Sentry<RoutingContext> {

    @Override
    public Handler<RoutingContext> signal(final Depot depot) {
        // continue to verify JsonObject/JsonArray type
        final Map<String, List<Rule>> rulers
                = verifier().buildRulers(depot);
        return (context) -> executeRequest(context, rulers, depot);
    }
}
