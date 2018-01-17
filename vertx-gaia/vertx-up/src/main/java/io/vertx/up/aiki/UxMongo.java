package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.log.Annal;
import io.vertx.up.plugin.mongo.MongoInfix;

import java.util.Objects;
import java.util.function.BinaryOperator;

class UxMongo {

    private static final MongoClient CLIENT = MongoInfix.getClient();

    private static final Annal LOGGER = Annal.get(UxMongo.class);

    static Future<Boolean> missing(final String collection, final JsonObject filter) {
        return Ux.thenGeneric(future -> CLIENT.findOne(collection, filter, null, res -> {
            LOGGER.debug(Info.MSG_FILTER, collection, filter, res.result());
            future.complete(null == res.result());
        }));
    }

    static Future<Boolean> existing(final String collection, final JsonObject filter) {
        return Ux.thenGeneric(future -> CLIENT.findOne(collection, filter, null, res -> {
            LOGGER.debug(Info.MSG_FILTER, collection, filter, res.result());
            future.complete(null != res.result());
        }));
    }

    static JsonObject termIn(final JsonObject filter, final String field, final JsonArray values) {
        final JsonObject terms = new JsonObject();
        if (null != filter) {
            terms.mergeIn(filter);
        }
        return terms.put(field, new JsonObject().put("$in", values));
    }

    static JsonObject termLike(final JsonObject filter, final String field, final String value) {
        final JsonObject terms = new JsonObject();
        if (null != filter) {
            terms.mergeIn(filter);
        }
        return terms.put(field, new JsonObject().put("$regex", ".*" + value + ".*"));
    }

    static Future<JsonObject> insert(final String collection, final JsonObject data) {
        return Ux.thenGeneric(future -> CLIENT.insert(collection, data, res -> {
            if (res.succeeded()) {
                LOGGER.debug(Info.MSG_INSERT, collection, data);
                future.complete(data);
            } else {
                LOGGER.debug(Info.MSG_INSERT, collection, null);
                future.complete();
            }
        }));
    }

    static Future<JsonObject> findOne(final String collection, final JsonObject filter) {
        return Ux.thenGeneric(future -> CLIENT.findOne(collection, filter, null, res -> {
            LOGGER.debug(Info.MSG_FILTER, collection, filter, res.result());
            future.complete(res.result());
        }));
    }

    static Future<JsonObject> findOne(final String collection, final JsonObject filter,
                                      final String joinedCollection, final String joinedKey, final JsonObject additional,
                                      final BinaryOperator<JsonObject> operatorFun) {
        final JsonObject data = new JsonObject();
        return findOne(collection, filter)
                .compose(result -> {
                    data.mergeIn(result);
                    final JsonObject joinedFilter = (null == additional) ? new JsonObject() : additional.copy();
                    // MongoDB only
                    joinedFilter.put(joinedKey, result.getValue("_id"));
                    return findOne(joinedCollection, joinedFilter);
                })
                .compose(second -> Future.succeededFuture(operatorFun.apply(data, second)));
    }

    static Future<JsonObject> findOneAndReplace(final String collection, final JsonObject filter,
                                                final JsonObject data) {
        return Ux.thenGeneric(future -> CLIENT.findOneAndReplace(collection, filter, data, result -> {
            LOGGER.debug(Info.MSG_UPDATE, collection, filter, data);
            future.complete(result.result());
        }));
    }

    static Future<Long> removeDocument(final String collection, final JsonObject filter) {
        return Ux.thenGeneric(future -> CLIENT.removeDocument(collection, filter, res -> {
            final Long removed = res.result().getRemovedCount();
            LOGGER.debug(Info.MSG_DELETE, collection, filter, removed);
            future.complete(removed);
        }));
    }

    static Future<JsonArray> findWithOptions(final String collection, final JsonObject filter,
                                             final FindOptions options) {
        return Ux.thenGeneric(future -> CLIENT.findWithOptions(collection, filter, options, res -> {
            final JsonArray result = new JsonArray();
            Observable.fromIterable(res.result())
                    .filter(Objects::nonNull)
                    .subscribe(result::add);
            LOGGER.debug(Info.MSG_FIND, collection, filter, options.toJson(), result);
            future.complete(result);
        }));
    }

    static Future<JsonArray> findWithOptions(final String collection, final JsonObject filter, final FindOptions options,
                                             // Secondary Query
                                             final String joinedCollection, final String joinedKey, final JsonObject additional,
                                             final BinaryOperator<JsonObject> operatorFun) {
        return Ux.thenParallelArray(findWithOptions(collection, filter, options),
                item -> {
                    final JsonObject joinedFilter = (null == additional) ? new JsonObject() : additional.copy();
                    // MongoDB only
                    joinedFilter.put(joinedKey, item.getValue("_id"));
                    return findOne(joinedCollection, joinedFilter);
                }, operatorFun);
    }
}
