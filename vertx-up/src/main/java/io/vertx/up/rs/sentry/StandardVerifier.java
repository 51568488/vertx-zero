package io.vertx.up.rs.sentry;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.validation.ValidationHandler;
import io.vertx.up.atom.Depot;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.Event;
import io.vertx.up.exception.WebException;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.hunt.Answer;
import io.vertx.up.rs.hunt.BaseAim;

import java.lang.reflect.Method;

/**
 * Major execution to verify the result.
 */
public class StandardVerifier extends BaseAim implements Sentry {

    @Override
    public ValidationHandler signal(final Depot depot) {
        return (context) -> {
            // Extract major object
            final WebException error = verifyPureArguments(context, depot);
            // 1.Basic validation passed.
            if (null == error) {
                // continue to verify JsonObject/JsonArray type
                
                context.next();
            } else {
                final Envelop envelop = Envelop.failure(error);
                Answer.reply(context, envelop, depot.getEvent());
            }
        };
    }

    private WebException verifyPureArguments(final RoutingContext context,
                                             final Depot depot) {
        final Event event = depot.getEvent();
        final Object proxy = event.getProxy();
        final Method method = event.getAction();
        final Object[] args = buildArgs(context, event);
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
