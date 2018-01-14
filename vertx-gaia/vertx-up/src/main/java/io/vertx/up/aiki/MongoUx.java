package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.up.log.Annal;
import io.vertx.up.plugin.mongo.MongoInfix;

class MongoUx {

    private static final MongoClient CLIENT = MongoInfix.getClient();

    private static final Annal LOGGER = Annal.get(MongoUx.class);

    static Future<Boolean> missing(final String collection, final JsonObject filter) {
        return Ux.thenGeneric(future -> CLIENT.findOne(collection, filter, null,
                res -> {
                    LOGGER.info(Info.MSG_FILTER, collection, filter, res.result());
                    future.complete(null == res.result());
                }));
    }

    static Future<JsonObject> insert(final String collection, final JsonObject data) {
        return Ux.thenGeneric(future -> CLIENT.insert(collection, data,
                res -> {
                    if (res.succeeded()) {
                        LOGGER.info(Info.MSG_INSERT, collection, data);
                        future.complete(data);
                    } else {
                        LOGGER.info(Info.MSG_INSERT, collection, null);
                        future.complete();
                    }
                }));
    }

    static Future<JsonObject> findOne(final String collection, final JsonObject filter) {
        return Ux.thenGeneric(future -> CLIENT.findOne(collection, filter, null,
                res -> {
                    LOGGER.info(Info.MSG_FILTER, collection, filter, res.result());
                    future.complete(res.result());
                }));
    }

    static Future<JsonObject> findOneAndReplace(final String collection, final JsonObject filter,
                                                final JsonObject data) {
        return Ux.thenGeneric(future -> CLIENT.findOneAndReplace(collection, filter, data,
                result -> {
                    LOGGER.info(Info.MSG_UPDATE, collection, filter, data);
                    future.complete(result.result());
                }));
    }
}
