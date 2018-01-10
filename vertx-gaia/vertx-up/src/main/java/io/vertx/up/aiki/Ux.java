package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
public final class Ux {

    public static <T> JsonObject toJson(final T entity) {
        return To.toJson(entity, "");
    }

    public static <T> JsonObject toJson(
            final T entity,
            final String pojo) {
        return To.toJson(entity, pojo);
    }

    public static <T> JsonObject toJson(
            final T entity,
            final Function<JsonObject, JsonObject> convert
    ) {
        return To.toJson(entity, convert);
    }

    public static WebException toError(
            final Class<? extends WebException> clazz,
            final Object... args
    ) {
        return To.toError(clazz, args);
    }

    public static <T> Future<Envelop> toEnvelop(
            final CompletableFuture<T> future
    ) {
        return Async.toFuture("", future);
    }


    public static <T> JsonArray toArray(
            final List<T> list) {
        return To.toArray(list, "");
    }

    public static <T> JsonArray toArray(
            final List<T> list,
            final String pojo) {
        return To.toArray(list, pojo);
    }

    public static <T> JsonArray toArray(
            final List<T> list,
            final Function<JsonObject, JsonObject> convert) {
        return To.toArray(list, convert);
    }

    public static <T> Envelop toEnvelop(
            final List<T> list
    ) {
        return Envelop.success(toArray(list));
    }

    public static <T> Envelop toEnvelop(
            final T entity
    ) {
        return To.toEnvelop(entity);
    }

    public static Envelop toOne(
            final JsonArray array
    ) {
        return Envelop.success(To.toUnique(array, ""));
    }

    public static <T> Envelop toEnvelop(
            final Class<? extends WebException> clazz,
            final Object... args
    ) {
        return To.toEnvelop(clazz, args);
    }

    public static <T> JsonObject toUnique(
            final List<T> list
    ) {
        return To.toUnique(toArray(list), "");
    }

    public static <T> JsonObject toUnique(
            final JsonArray array
    ) {
        return To.toUnique(array, "");
    }

    public static JsonObject getJson(final Message<Envelop> message) {
        return In.request(message, 0, JsonObject.class);
    }

    public static JsonObject getBody(final Message<Envelop> message) {
        return In.request(message, JsonObject.class);
    }

    public static String getString(final Message<Envelop> message, final int index) {
        return In.request(message, index, String.class);
    }

    public static Integer getInteger(final Message<Envelop> message, final int index) {
        return In.request(message, index, Integer.class);
    }

    public static Long getLong(final Message<Envelop> message, final int index) {
        return In.request(message, index, Long.class);
    }
}
