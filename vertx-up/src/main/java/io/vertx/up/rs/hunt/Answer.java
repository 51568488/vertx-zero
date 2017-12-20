package io.vertx.up.rs.hunt;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;

/**
 * Response process to normalize the response request.
 * 1. Media definition
 * 2. Operation based on event, envelop, context
 */
public final class Answer {

    public static void reply(
            final RoutingContext context,
            final Envelop envelop,
            final Event event
    ) {
        // 1. Get response reference
        final HttpServerResponse response
                = context.response();

        // 2. Set response status
        final HttpStatusCode code = envelop.status();
        response.setStatusCode(code.code());
        response.setStatusMessage(code.message());

        // 3. Media processing
        Normalizer.out(response, envelop, event);
        // 3. Response process
        if (!response.ended()) {
            response.end(envelop.response());
        }
        response.close();
    }
}
