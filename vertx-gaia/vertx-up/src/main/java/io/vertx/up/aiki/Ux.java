package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
@SuppressWarnings("unchecked")
public final class Ux {
    /**
     * Json calculation
     */
    public static class Json {

        public static JsonObject deNull(final JsonObject entity) {
            return Self.deNull(entity, false);
        }

        public static JsonObject deNullNew(final JsonObject entity) {
            return Self.deNull(entity, true);
        }

        public static JsonObject merge(final JsonObject target, final JsonObject source) {
            return Dual.merge(target, source, false);
        }

        public static JsonObject appendNew(final JsonObject target, final JsonObject source) {
            return Dual.merge(target, source, true);
        }
    }

    public static <T> JsonObject toJson(final T entity) {
        return To.toJson(entity, "");
    }

    public static <T> JsonObject toJson(final T entity, final String pojo) {
        return To.toJson(entity, pojo);
    }

    public static <T> JsonObject toJson(final T entity, final Function<JsonObject, JsonObject> convert
    ) {
        return To.toJson(entity, convert);
    }

    public static WebException toError(final Class<? extends WebException> clazz, final Object... args
    ) {
        return To.toError(clazz, args);
    }

    public static <T> JsonArray toArray(final List<T> list) {
        return To.toArray(list, "");
    }

    public static <T> JsonArray toArray(final List<T> list, final String pojo) {
        return To.toArray(list, pojo);
    }

    public static <T> JsonArray toArray(final List<T> list, final Function<JsonObject, JsonObject> convert) {
        return To.toArray(list, convert);
    }

    public static <T> Envelop to(final List<T> list) {
        return Envelop.success(Ux.toArray(list));
    }

    public static Envelop to(final Class<? extends WebException> clazz, final Object... args) {
        return To.toEnvelop(clazz, args);
    }

    public static <T> Envelop to(final T entity) {
        return To.toEnvelop(entity);
    }

    public static Envelop toOne(
            final JsonArray array
    ) {
        return Envelop.success(To.toUnique(array, ""));
    }

    public static <T> JsonObject toUnique(
            final List<T> list
    ) {
        return To.toUnique(Ux.toArray(list), "");
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

    public static <T> Future<JsonObject> thenJson(final CompletableFuture<T> future) {
        return Async.toJsonFuture("", future);
    }

    public static <T> Future<JsonObject> thenJson(final String pojo, final CompletableFuture<T> future
    ) {
        return Async.toJsonFuture(pojo, future);
    }

    private static <T> Future<JsonArray> thenArray(final CompletableFuture<List<T>> future) {
        return Async.toArrayFuture("", future);
    }

    private static <T> Future<JsonArray> thenArray(final String pojo, final CompletableFuture<List<T>> future) {
        return Async.toArrayFuture(pojo, future);
    }

    public static <T> Future<Envelop> then(final CompletableFuture<T> future) {
        return Async.toSingle("", future);
    }

    public static <T> Future<T> thenGeneric(final CompletableFuture<T> future) {
        return Async.toFuture(future);
    }

    public static <T> Future<Envelop> then(final String pojo, final CompletableFuture<T> future) {
        return Async.toSingle(pojo, future);
    }

    public static <T> Future<Envelop> thenMore(final CompletableFuture<List<T>> future) {
        return Async.toMulti("", future);
    }

    public static <T> Future<Envelop> thenMore(final String pojo, final CompletableFuture<List<T>> future) {
        return Async.toMulti(pojo, future);
    }

    public static <T> Future<Envelop> thenUnique(final CompletableFuture<List<T>> future) {
        return Async.toUnique("", future);
    }

    public static <T> Future<Envelop> thenUnique(final String pojo, final CompletableFuture<List<T>> future) {
        return Async.toUnique(pojo, future);
    }

    public static <F, S, T> Future<T> thenComposite(final BiFunction<F, List<S>, T> mergeFun, final Future<F> source, final Supplier<Future<S>>... suppliers) {
        return Fluctuate.thenComposite(mergeFun, source, suppliers);
    }

    public static <F, S, T> Future<T> thenComposite(final BiFunction<F, List<S>, T> mergeFun, final Future<F> source, final Function<F, Future<S>>... functions) {
        return Fluctuate.thenComposite(mergeFun, source, functions);
    }
}
