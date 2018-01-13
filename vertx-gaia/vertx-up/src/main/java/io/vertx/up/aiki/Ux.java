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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
@SuppressWarnings("unchecked")
public final class Ux {
    // ---------------------- JsonObject Returned --------------------------
    // T -> JsonObject
    public static <T> JsonObject toJson(final T entity) {
        return To.toJson(entity, "");
    }

    // T -> JsonObject ( with Pojo )
    public static <T> JsonObject toJson(final T entity, final String pojo) {
        return To.toJson(entity, pojo);
    }

    // T -> JsonObject ( with convert )
    public static <T> JsonObject toJson(final T entity, final Function<JsonObject, JsonObject> convert
    ) {
        return To.toJson(entity, convert);
    }

    // ---------------------- Web Error Returned --------------------------
    // -> WebException direct
    public static WebException toError(final Class<? extends WebException> clazz, final Object... args
    ) {
        return To.toError(clazz, args);
    }

    // ---------------------- JsonArray Returned --------------------------
    // -> List<T> -> JsonArray
    public static <T> JsonArray toArray(final List<T> list) {
        return To.toArray(list, "");
    }

    // -> List<T> -> JsonArray ( with Pojo )
    public static <T> JsonArray toArray(final List<T> list, final String pojo) {
        return To.toArray(list, pojo);
    }

    // -> List<T> -> JsonArray ( convert )
    public static <T> JsonArray toArray(final List<T> list, final Function<JsonObject, JsonObject> convert) {
        return To.toArray(list, convert);
    }

    // ---------------------- Envelop Returned --------------------------
    // -> List<T> -> Envelop
    public static <T> Envelop to(final List<T> list) {
        return Envelop.success(Ux.toArray(list));
    }

    // -> Class<?> -> Envelop ( With failure )
    public static Envelop to(final Class<? extends WebException> clazz, final Object... args) {
        return To.toEnvelop(clazz, args);
    }

    // -> T -> Envelop
    public static <T> Envelop to(final T entity) {
        return To.toEnvelop(entity);
    }

    // -> T -> Envelop ( If entity is null, return Envelop.failure(error) )
    public static <T> Envelop to(final T entity, final WebException error) {
        return To.toEnvelop(entity, error);
    }

    // -> JsonArray -> Envelop ( To JsonObject, Result length must be 1 )
    public static Envelop toOne(
            final JsonArray array
    ) {
        return Envelop.success(To.toUnique(array, ""));
    }

    // -> List<T> -> JsonObject ( Result length must be 1 )
    public static <T> JsonObject toUnique(
            final List<T> list
    ) {
        return To.toUnique(Ux.toArray(list), "");
    }

    // -> JsonArray -> JsonObject ( Result length must be 1 )
    public static <T> JsonObject toUnique(
            final JsonArray array
    ) {
        return To.toUnique(array, "");
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson(final Message<Envelop> message) {
        return In.request(message, 0, JsonObject.class);
    }

    // -> Message<Envelop> -> JsonObject ( Agent mode )
    public static JsonObject getBody(final Message<Envelop> message) {
        return In.request(message, JsonObject.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString(final Message<Envelop> message, final int index) {
        return In.request(message, index, String.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger(final Message<Envelop> message, final int index) {
        return In.request(message, index, Integer.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong(final Message<Envelop> message, final int index) {
        return In.request(message, index, Long.class);
    }

    // -> CompletableFuture<T> -> Future<JsonObject> ( Async )
    public static <T> Future<JsonObject> thenJson(final CompletableFuture<T> future) {
        return Async.toJsonFuture("", future);
    }

    // -> CompletableFuture<T> -> Future<JsonObject> ( Async with Pojo )
    public static <T> Future<JsonObject> thenJson(final String pojo, final CompletableFuture<T> future
    ) {
        return Async.toJsonFuture(pojo, future);
    }

    // -> CompletableFuture<List<T>> -> Future<JsonArray> ( Async )
    private static <T> Future<JsonArray> thenArray(final CompletableFuture<List<T>> future) {
        return Async.toArrayFuture("", future);
    }

    // -> CompletableFuture<List<T>> -> Future<JsonArray> ( Async with pojo )
    private static <T> Future<JsonArray> thenArray(final String pojo, final CompletableFuture<List<T>> future) {
        return Async.toArrayFuture(pojo, future);
    }

    // -> CompletableFuture<T> -> Future<Envelop> ( Async )
    public static <T> Future<Envelop> then(final CompletableFuture<T> future) {
        return Async.toSingle("", future);
    }


    // -> CompletableFuture<T> -> Future<Envelop> ( Async with pojo )
    public static <T> Future<Envelop> then(final String pojo, final CompletableFuture<T> future) {
        return Async.toSingle(pojo, future);
    }

    // -> CompletableFuture<T> -> Future<T> ( Async direct to Future<T> )
    public static <T> Future<T> thenGeneric(final CompletableFuture<T> future) {
        return Async.toFuture(future);
    }

    // -> Consumer<Future<T>> -> Future<T>
    public static <T> Future<T> thenGeneric(final Consumer<Future<T>> consumer) {
        return Wait.then(consumer);
    }

    // -> CompletableFuture<List<T>> -> Future<Envelop> ( Async )
    public static <T> Future<Envelop> thenMore(final CompletableFuture<List<T>> future) {
        return Async.toMulti("", future);
    }

    // -> CompletableFuture<List<T>> -> Future<Envelop> ( Async with Pojo )
    public static <T> Future<Envelop> thenMore(final String pojo, final CompletableFuture<List<T>> future) {
        return Async.toMulti(pojo, future);
    }

    // -> CompletableFuture<List<T>> -> Future<Envelop> ( Async , Result length must be 1)
    public static <T> Future<Envelop> thenUnique(final CompletableFuture<List<T>> future) {
        return Async.toUnique("", future);
    }

    // -> CompletableFuture<List<T>> -> Future<Envelop> ( Async with Pojo, Result length must be 1)
    public static <T> Future<Envelop> thenUnique(final String pojo, final CompletableFuture<List<T>> future) {
        return Async.toUnique(pojo, future);
    }

    /**
     * Merge multi Future<> to single one, one for all module.
     * source ->
     * supplier1
     * supplier2
     * supplier3
     * .....
     * All suppliers will be executed after source, then return the final Future.
     *
     * @param mergeFun  How to merge source result and all supplier's results into final result:
     * @param source    Single Future --> F
     * @param suppliers Multi Futures --> List<S>
     * @param <F>       Type of source element
     * @param <S>       Type of supplier's list element
     * @param <T>       Type of return
     * @return Future<T> for final result.
     */
    public static <F, S, T> Future<T> thenComposite(final BiFunction<F, List<S>, T> mergeFun, final Future<F> source, final Supplier<Future<S>>... suppliers) {
        return Fluctuate.thenComposite(mergeFun, source, suppliers);
    }

    // -> Merge multi Future<> to single one, one for all module.
    public static <F, S, T> Future<T> thenComposite(final BiFunction<F, List<S>, T> mergeFun, final Future<F> source, final Function<F, Future<S>>... functions) {
        return Fluctuate.thenComposite(mergeFun, source, functions);
    }


}