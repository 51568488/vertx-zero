package io.vertx.up.rs.sentry;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.validation.ValidationHandler;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Ruler;
import io.vertx.up.exception.WebException;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.hunt.Answer;
import io.vertx.up.rs.hunt.BaseAim;
import io.vertx.zero.eon.Values;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Major execution to verify the result.
 */
public class StandardVerifier extends BaseAim implements Sentry {

    @Override
    public ValidationHandler signal(final Depot depot) {
        // continue to verify JsonObject/JsonArray type
        final ConcurrentMap<String, List<Ruler>> rulers
                = verifier().buildRulers(depot);
        return (context) -> {
            final Object[] args = buildArgs(context, depot.getEvent());
            // Extract major object
            WebException error = verifyPureArguments(context, depot, args);
            // 1.Basic validation passed.
            if (null == error) {
                // 2. Body validation for jsonobject
                error = verifyBody(rulers, args);
                
                context.next();
            } else {
                final Envelop envelop = Envelop.failure(error);
                Answer.reply(context, envelop, depot.getEvent());
            }
        };
    }

    private WebException verifyBody(
            final ConcurrentMap<String, List<Ruler>> rulers,
            final Object[] args) {
        final WebException error = null;
        if (!rulers.isEmpty()) {
            // Extract the first element
            final Object body = args[Values.IDX];
            if (null != body) {
                final JsonObject data = (JsonObject) body;
                System.out.println(data.encode());
            }
        }
        return error;
    }

    private WebException verifyPureArguments(
            final RoutingContext context,
            final Depot depot,
            final Object[] args) {
        final Event event = depot.getEvent();
        final Object proxy = event.getProxy();
        final Method method = event.getAction();
        WebException error = null;
        try {
            // Validation first
            verifier().verifyMethod(proxy, method, args);
        } catch (final WebException ex) {
            // Basic validation failure
            error = ex;
        }
        return error;
    }
}
