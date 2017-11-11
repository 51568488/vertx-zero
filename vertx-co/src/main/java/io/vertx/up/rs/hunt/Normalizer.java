package io.vertx.up.rs.hunt;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.up.ce.Event;

import javax.ws.rs.core.MediaType;

/**
 * Content-Type
 * Accept
 */
public final class Normalizer {
    /**
     * Wrapper response
     *
     * @param response
     * @param event
     */
    public static void out(final HttpServerResponse response,
                           final Event event) {
        response.putHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
    }

    /**
     * Wrapper request
     *
     * @param request
     * @param event
     */
    public static void in(final HttpServerRequest request,
                          final Event event) {

    }
}
