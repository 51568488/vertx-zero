package io.vertx.up.rs.reflect;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

/**
 * Arguments process by type, TypedFilter.
 */
public class TypedFilter {

    public static Object get(final Class<?> paramType,
                             final RoutingContext context) {
        Object returnValue = null;
        if (Session.class == paramType) {
            // Session Object
            returnValue = context.session();
        } else if (HttpServerRequest.class == paramType) {
            // Request Object
            returnValue = context.request();
        } else if (HttpServerResponse.class == paramType) {
            // Response Object
            returnValue = context.response();
        } else if (Vertx.class == paramType) {
            // Vertx Object
            returnValue = context.vertx();
        } else if (EventBus.class == paramType) {
            // Eventbus Object
            returnValue = context.vertx().eventBus();
        } else if (User.class == paramType) {
            // User Objbect
            returnValue = context.user();
        }
        return returnValue;
    }
}
