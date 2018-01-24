package io.vertx.tp.plugin.qiy;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.QiyAuthorizedException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

class QiyRepdor {
    private static final String CODE = "code";
    private static final String DATA = "data";
    private static final String MSG = "msg";
    private static final Annal LOGGER = Annal.get(QiyRepdor.class);

    static Future<JsonObject> handle(final JsonObject response) {
        LOGGER.info(Info.FEIGN_RESPONSE, response);
        Fn.flingWeb(!QiyCodes.SUCCESS.equals(response.getString(CODE)), LOGGER,
                QiyAuthorizedException.class, QiyRepdor.class,
                response.getString(CODE),
                response.getString(MSG));
        return Future.succeededFuture(response.getJsonObject(DATA));
    }
}
