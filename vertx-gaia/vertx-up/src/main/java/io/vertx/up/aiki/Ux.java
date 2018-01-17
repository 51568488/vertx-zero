package io.vertx.up.aiki;

import io.github.jklingsporn.vertx.jooq.future.VertxDAO;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.query.Pager;
import io.vertx.up.atom.query.Sorter;
import io.vertx.up.exception.WebException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.*;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
@SuppressWarnings("unchecked")
public final class Ux {
    // Business method
    // page, size -> JsonObject
    public static JsonObject toPagerJson(final int page, final int size) {
        return Pagination.toPager(page, size).toJson();
    }

    // page, size -> Pager
    public static Pager toPager(final int page, final int size) {
        return Pagination.toPager(page, size);
    }

    // JsonObject -> Pager
    public static Pager toPager(final JsonObject data) {
        return Pagination.toPager(data);
    }

    // field, asc -> Sorter
    public static Sorter toSorter(final String field, final boolean asc) {
        return Pagination.toSorter(field, asc);
    }

    // field, mode -> Sorter
    public static Sorter toSorter(final String field, final int mode) {
        return Pagination.toSorter(field, mode);
    }

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
    public static <T> JsonObject toJsonFun(final T entity, final Function<JsonObject, JsonObject> convert
    ) {
        return To.toJson(entity, convert);
    }

    // ---------------------- Web Error Returned --------------------------
    // -> WebException direct
    public static WebException toError(final Class<? extends WebException> clazz, final Object... args
    ) {
        return To.toError(clazz, args);
    }

    // -> WebException transfer
    public static WebException toError(
            final Class<?> clazz,
            final Throwable error
    ) {
        return To.toError(clazz, error);
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
    public static <T> JsonArray toArrayFun(final List<T> list, final Function<JsonObject, JsonObject> convert) {
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

    // ---------------------- User Data -------------------------------------

    // -> Message<Envelop> -> String ( Security )
    public static String getUserID(final Message<Envelop> message, final String field) {
        return In.requestUser(message, field);
    }

    // -> Message<Envelop> -> UUID ( Security )
    public static UUID getUserUUID(final Message<Envelop> message, final String field) {
        return UUID.fromString(getUserID(message, field));
    }

    // -> Message<Envelop> -> Session ( Key )
    public static Object getSession(final Message<Envelop> message, final String field) {
        return In.requestSession(message, field);
    }

    // ---------------------- Web Flow --------------------------------------
    public static <T> Handler<AsyncResult<T>> toHandler(final Message<Envelop> message) {
        return Web.toHandler(message);
    }

    public static <T> Handler<AsyncResult<Boolean>> toHandler(final Message<Envelop> message, final JsonObject data) {
        return Web.toHandler(message, data);
    }

    // ---------------------- Request Data Extract --------------------------
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

    // -> Message<Envelop> -> T ( Agent mode )
    public static <T> T getBodyT(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static <T> T getJsonT(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, 0, clazz);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger(final Message<Envelop> message, final int index) {
        return In.request(message, index, Integer.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong(final Message<Envelop> message, final int index) {
        return In.request(message, index, Long.class);
    }

    // ---------------------- Future --------------------------
    public static <T> Future<JsonObject> thenRpc(final String name, final String address, final JsonObject params) {
        return UxRpc.thenRpc(name, address, params);
    }

    // ---------------------- New future ----------------------
    public static <T> Future<Envelop> thenMore(final List<T> list, final String pojo) {
        return Future.succeededFuture(Envelop.success(To.toArray(list, pojo)));
    }

    // ---------------------- Future --------------------------
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
     * Parallel generate
     * Source ->
     * source1 -> Future<1>
     * source2 -> Future<2>
     * For each element merge 1,2 -> 3
     *
     * @param source      List<F>
     * @param generateFun F -> Future<S>
     * @param mergeFun    Each element: (F,S) -> T
     * @param <F>         first
     * @param <S>         second
     * @param <T>         third
     * @return List<T>
     */
    public static <F, S, T> Future<List<T>> thenParallel(final Future<List<F>> source, final Function<F, Future<S>> generateFun, final BiFunction<F, S, T> mergeFun) {
        return Fluctuate.thenParallel(source, generateFun, mergeFun);
    }

    public static Future<JsonArray> thenParallelJson(final Future<JsonArray> source, final Function<JsonObject, Future<JsonObject>> generateFun, final BinaryOperator<JsonObject> operatorFun) {
        return Fluctuate.thenParallelJson(source, generateFun, operatorFun);
    }

    /**
     * Scatter generate
     * Source ->
     * source1 -> Future<List<1>>
     * source2 -> Future<List<2>>
     * Fore each element mergage 1, List<2> -> 3
     *
     * @param source      JsonArray
     * @param generateFun JsonObject -> Future<JsonArray>
     * @param mergeFun    Each element: JsonObject + JsonArray -> JsonObject
     * @return JsonArray
     */
    public static Future<JsonArray> thenScatterJson(final Future<JsonArray> source, final Function<JsonObject, Future<JsonArray>> generateFun, final BiFunction<JsonObject, JsonArray, JsonObject> mergeFun) {
        return Fluctuate.thenScatterJson(source, generateFun, mergeFun);
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
    public static <F, S, T> Future<T> thenComposite(final Future<F> source, final BiFunction<F, List<S>, T> mergeFun, final Supplier<Future<S>>... suppliers) {
        return Fluctuate.thenComposite(source, mergeFun, suppliers);
    }

    // -> Merge multi Future<> to single one, one for all module.
    public static <F, S, T> Future<T> thenComposite(final Future<F> source, final BiFunction<F, List<S>, T> mergeFun, final Function<F, Future<S>>... functions) {
        return Fluctuate.thenComposite(source, mergeFun, functions);
    }

    // -> IfElse true -> Future<T>, false -> Future<F>
    public static <T, F, R> Future<R> thenOtherwise(final Future<Boolean> condition, final Supplier<Future<T>> trueFuture, final Function<T, R> trueFun, final Supplier<Future<F>> falseFuture, final Function<F, R> falseFun) {
        return Fluctuate.thenOtherwise(condition, trueFuture, trueFun, falseFuture, falseFun);
    }

    // -> IfOr true -> Future<T>, false -> Future<R>
    public static <T, R> Future<R> thenError(final Future<Boolean> condition, final Supplier<Future<T>> trueFuture, final Function<T, R> trueFun, final Class<? extends WebException> clazz, final Object... args) {
        return Fluctuate.thenOtherwise(condition, trueFuture, trueFun, clazz, args);
    }

    // -> IfOr true -> Future<JsonObject>, false -> Future<JsonObject>
    public static Future<JsonObject> thenError(final Future<Boolean> condition, final Supplier<Future<JsonObject>> trueFuture, final Class<? extends WebException> clazz, final Object... args) {
        return Fluctuate.thenOtherwise(condition, trueFuture, item -> item, clazz, args);
    }

    // -> To error directly
    public static <T> Future<T> thenError(final Class<? extends WebException> clazz, final Object... args) {
        return Fluctuate.thenError(clazz, args);
    }

    // -> If only true -> Future<T>
    public static <T, R> Future<R> thenTrue(final Future<Boolean> condition, final Supplier<Future<T>> trueFuture, final Function<T, R> trueFun) {
        return Fluctuate.thenOtherwise(condition, trueFuture, trueFun, null);
    }

    // -> Jooq
    public static class Jooq {

        public static <T, D extends VertxDAO> Future<List<T>> fetchByAsync(final String column, final String value) {
            return UxJooq.<T, D>fetchByAsync(column, value);
        }
    }

    // -> Mongo
    public static class Mongo {

        public static JsonObject termIn(final JsonObject filter, final String field, final JsonArray values) {
            return UxMongo.termIn(filter, field, values);
        }

        public static JsonObject termLike(final JsonObject filter, final String field, final String value) {
            return UxMongo.termLike(filter, field, value);
        }

        public static Future<Boolean> missing(final String collection, final JsonObject filter) {
            return UxMongo.missing(collection, filter);
        }

        public static Future<Boolean> existing(final String collection, final JsonObject filter) {
            return UxMongo.existing(collection, filter);
        }

        public static Future<JsonObject> insert(final String collection, final JsonObject data) {
            return UxMongo.insert(collection, data);
        }

        public static Future<JsonObject> findOne(final String collection, final JsonObject filter) {
            return UxMongo.findOne(collection, filter);
        }

        public static Future<JsonObject> findOne(final String collection, final JsonObject filter,
                                                 final String joinedCollection, final String joinedKey, final JsonObject additional,
                                                 final BinaryOperator<JsonObject> operatorFun) {
            return UxMongo.findOne(collection, filter, joinedCollection, joinedKey, additional, operatorFun);
        }

        public static Future<JsonObject> findOneAndReplace(final String collection, final JsonObject filter,
                                                           final String field, final Object value) {
            return UxMongo.findOneAndReplace(collection, filter, new JsonObject().put(field, value));
        }

        public static Future<JsonObject> findOneAndReplace(final String collection, final JsonObject filter,
                                                           final JsonObject data) {
            return UxMongo.findOneAndReplace(collection, filter, data);
        }

        public static Future<Long> removeDocument(final String collection, final JsonObject filter) {
            return UxMongo.removeDocument(collection, filter);
        }

        public static Future<JsonArray> findWithOptions(final String collection, final JsonObject filter,
                                                        final FindOptions options) {
            return UxMongo.findWithOptions(collection, filter, options);
        }

        public static Future<JsonArray> findWithOptions(final String collection, final JsonObject filter, final FindOptions options,
                                                        // Secondary Query
                                                        final String joinedCollection, final String joinedKey, final JsonObject additional,
                                                        final BinaryOperator<JsonObject> operatorFun) {
            return UxMongo.findWithOptions(collection, filter, options,
                    joinedCollection, joinedKey, additional, operatorFun);
        }

        public static Future<JsonArray> find(final String collection, final JsonObject filter) {
            return UxMongo.findWithOptions(collection, filter, new FindOptions());
        }
    }
}
