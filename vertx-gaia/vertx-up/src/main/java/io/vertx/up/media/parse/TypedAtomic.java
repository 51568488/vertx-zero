package io.vertx.up.media.parse;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.up.atom.Epsilon;
import io.vertx.up.exception.WebException;

import java.util.Set;

@SuppressWarnings("unchecked")
public class TypedAtomic<T> implements Atomic<T> {
    @Override
    public Epsilon<T> ingest(final RoutingContext context,
                             final Epsilon<T> income)
            throws WebException {
        Object returnValue = null;
        final Class<?> paramType = income.getArgType();
        if (is(Session.class, paramType)) {
            // Session Object
            returnValue = context.session();
        } else if (is(HttpServerRequest.class, paramType)) {
            // Request Object
            returnValue = context.request();
        } else if (is(HttpServerResponse.class, paramType)) {
            // Response Object
            returnValue = context.response();
        } else if (is(Vertx.class, paramType)) {
            // Vertx Object
            returnValue = context.vertx();
        } else if (is(EventBus.class, paramType)) {
            // Eventbus Object
            returnValue = context.vertx().eventBus();
        } else if (is(User.class, paramType)) {
            // User Objbect
            returnValue = context.user();
        } else if (is(Set.class, paramType)) {
            // FileUpload
            final Class<?> type = paramType.getComponentType();
            if (is(FileUpload.class, type)) {
                returnValue = context.fileUploads();
            }
        } else if (is(JsonArray.class, paramType)) {
            // JsonArray
            returnValue = context.getBodyAsJsonArray();
        } else if (is(JsonObject.class, paramType)) {
            // JsonObject
            returnValue = context.getBodyAsJson();
        } else if (is(Buffer.class, paramType)) {
            // Buffer
            returnValue = context.getBody();
        }
        return null == returnValue ? income.setValue(null) : income.setValue((T) returnValue);
    }

    private boolean is(final Class<?> expected, final Class<?> paramType) {
        return expected == paramType || expected.isAssignableFrom(paramType);
    }
}
