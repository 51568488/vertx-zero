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

class MongoUx {

    private static final MongoClient CLIENT = MongoInfix.getClient();

    private static final Annal LOGGER = Annal.get(MongoUx.class);

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
}
