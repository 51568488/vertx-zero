package io.vertx.up.aiki;

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
import io.vertx.up.func.Fn;

import java.util.List;
import java.util.UUID;
import java.util.function.*;

/**
 * Here Ux is a util interface of uniform to call different tools.
 * It just like helper for business usage.
 */
@SuppressWarnings("unchecked")
public final class Ux {
    /**
     * Debug only
     *
     * @param objects
     */
    public static void debug(final Object... objects) {
        for (final Object reference : objects) {
            Debug.monitor(reference);
        }
    }

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

    // Special Merge
    public static void appendJson(final JsonObject target, final JsonObject source) {
        Calculator.appendJson(target, source);
    }

    // ---------------------- Web Error Returned --------------------------
    // -> WebException direct
    public static WebException toError(final Class<? extends WebException> clazz, final Object... args) {
        return To.toError(clazz, args);
    }

    // -> WebException transfer
    public static WebException toError(final Class<?> clazz, final Throwable error) {
        return To.toError(clazz, error);
    }


    // ---------------------- Future --------------------------

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
    public static Envelop toOne(final JsonArray array) {
        return Envelop.success(To.toUnique(array, ""));
    }

    // -> List<T> -> JsonObject ( Result length must be 1 )
    public static <T> JsonObject toUnique(final List<T> list) {
        return To.toUnique(Ux.toArray(list), "");
    }

    public static <T> JsonObject toUnique(final List<T> list, final String pojo) {
        return To.toUnique(Ux.toArray(list), pojo);
    }

    // -> JsonArray -> JsonObject ( Result length must be 1 )
    public static <T> JsonObject toUnique(final JsonArray array) {
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
    // -> Message<Envelop> -> T ( Interface mode )
    public static JsonArray getArray(final Message<Envelop> message, final int index) {
        return In.request(message, index, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static JsonArray getArray(final Message<Envelop> message) {
        return In.request(message, 0, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static JsonArray getArray1(final Message<Envelop> message) {
        return In.request(message, 1, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static JsonArray getArray2(final Message<Envelop> message) {
        return In.request(message, 2, JsonArray.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static JsonArray getArray3(final Message<Envelop> message) {
        return In.request(message, 3, JsonArray.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString(final Message<Envelop> message, final int index) {
        return In.request(message, index, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString(final Message<Envelop> message) {
        return In.request(message, 0, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString1(final Message<Envelop> message) {
        return In.request(message, 1, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString2(final Message<Envelop> message) {
        return In.request(message, 2, String.class);
    }

    // -> Message<Envelop> -> String ( Interface mode )
    public static String getString3(final Message<Envelop> message) {
        return In.request(message, 3, String.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson(final Message<Envelop> message, final int index) {
        return In.request(message, index, JsonObject.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson(final Message<Envelop> message) {
        return In.request(message, 0, JsonObject.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson1(final Message<Envelop> message) {
        return In.request(message, 1, JsonObject.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson2(final Message<Envelop> message) {
        return In.request(message, 2, JsonObject.class);
    }

    // -> Message<Envelop> -> JsonObject ( Interface mode )
    public static JsonObject getJson3(final Message<Envelop> message) {
        return In.request(message, 3, JsonObject.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger(final Message<Envelop> message, final int index) {
        return In.request(message, index, Integer.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger1(final Message<Envelop> message) {
        return In.request(message, 1, Integer.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger2(final Message<Envelop> message) {
        return In.request(message, 2, Integer.class);
    }

    // -> Message<Envelop> -> Integer ( Interface mode )
    public static Integer getInteger3(final Message<Envelop> message) {
        return In.request(message, 3, Integer.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong(final Message<Envelop> message, final int index) {
        return In.request(message, index, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong(final Message<Envelop> message) {
        return In.request(message, 0, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong1(final Message<Envelop> message) {
        return In.request(message, 1, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong2(final Message<Envelop> message) {
        return In.request(message, 2, Long.class);
    }

    // -> Message<Envelop> -> Long ( Interface mode )
    public static Long getLong3(final Message<Envelop> message) {
        return In.request(message, 3, Long.class);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static <T> T getT(final Message<Envelop> message, final int index, final Class<T> clazz) {
        return In.request(message, index, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static <T> T getT(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, 0, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static <T> T getT1(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, 1, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static <T> T getT2(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, 2, clazz);
    }

    // -> Message<Envelop> -> T ( Interface mode )
    public static <T> T getT3(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, 3, clazz);
    }

    // ---------------------- Agent mode usage --------------------------
    // -> Message<Envelop> -> JsonObject ( Agent mode )
    public static JsonObject getBody(final Message<Envelop> message) {
        return In.request(message, JsonObject.class);
    }

    // -> Message<Envelop> -> T ( Agent mode )
    public static <T> T getBodyT(final Message<Envelop> message, final Class<T> clazz) {
        return In.request(message, clazz);
    }
    // ---------------------- Request Data Ending --------------------------


    // ---------------------- Future --------------------------
    public static <T> Future<JsonObject> thenRpc(final String name, final String address, final JsonObject params) {
        return UxRpc.thenRpc(name, address, params);
    }

    public static <T> Future<JsonObject> thenRpc(final String name, final String address, final String field, final Object value) {
        return UxRpc.thenRpc(name, address, new JsonObject().put(field, value));
    }

    // ---------------------- New future ----------------------
    public static <T> Future<JsonArray> thenJsonMore(final List<T> list, final String pojo) {
        return Future.succeededFuture(To.toArray(list, pojo));
    }

    public static <T> Future<JsonObject> thenJsonOne(final List<T> list, final String pojo) {
        return Future.succeededFuture(To.toUnique(new JsonArray(list), pojo));
    }

    public static <T> Future<JsonObject> thenJsonOne(final T entity, final String pojo) {
        return Future.succeededFuture(To.toJson(entity, pojo));
    }

    public static <T> Future<Envelop> thenMore(final List<T> list, final String pojo) {
        return Future.succeededFuture(Envelop.success(To.toArray(list, pojo)));
    }

    public static <T> Future<Envelop> thenOne(final T entity, final String pojo) {
        return Future.succeededFuture(Envelop.success(To.toJson(entity, pojo)));
    }

    // -> Consumer<Future<T>> -> Future<T>
    public static <T> Future<T> thenGeneric(final Consumer<Future<T>> consumer) {
        return Wait.then(consumer);
    }

    // ---------------------- Future --------------------------

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

    public static Future<JsonArray> thenParallelArray(final Future<JsonArray> source, final Function<JsonObject, Future<JsonObject>> generateFun, final BinaryOperator<JsonObject> operatorFun) {
        return Fluctuate.thenParallelArray(source, generateFun, operatorFun);
    }

    public static Future<JsonObject> thenParallelJson(final Future<JsonObject> source, final Function<JsonObject, List<Future>> generateFun, final BiConsumer<JsonObject, JsonObject>... operatorFun) {
        return Fluctuate.thenParallelJson(source, generateFun, operatorFun);
    }

    public static Future<JsonObject> thenParallelJson(final JsonObject source, final Function<JsonObject, List<Future>> generateFun, final BiConsumer<JsonObject, JsonObject>... operatorFun) {
        return Fluctuate.thenParallelJson(Future.succeededFuture(source), generateFun, operatorFun);
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

        public static UxJooq on(final Class<?> clazz) {
            return Fn.pool(Pool.JOOQ, clazz, () -> new UxJooq(clazz));
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
