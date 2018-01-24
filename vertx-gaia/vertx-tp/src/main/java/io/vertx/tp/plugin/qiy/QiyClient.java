package io.vertx.tp.plugin.qiy;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * QiyClient for platform of http://open.iqiyi.com/
 * Video open sdk
 */
public interface QiyClient {

    static QiyClient createShared(final Vertx vertx) {
        return new QiyClientImpl(vertx, QiyToken.create());
    }

    @Fluent
    QiyClient init(final JsonObject params);

    /**
     * /iqiyi/authorize
     */
    @Fluent
    QiyClient authorize(Handler<AsyncResult<JsonObject>> handler);

    /**
     * /oauth2/token
     */
    @Fluent
    QiyClient refreshToken(String refreshToken,
                           Handler<AsyncResult<JsonObject>> handler);

    /**
     * /openupload
     */
    @Fluent
    QiyClient requestUpload(String fileType,
                            String size,
                            Handler<AsyncResult<JsonObject>> handler);
}
