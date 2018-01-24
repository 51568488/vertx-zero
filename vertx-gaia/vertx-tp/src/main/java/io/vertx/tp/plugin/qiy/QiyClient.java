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
    /**
     * Default Endpoint, Fixed
     */
    String DEFAULT_ENDPOINT = "https://openapi.iqiyi.com/api";
    /**
     * Client ID
     */
    String KEY_ID = "client_id";
    /**
     * Client Secret
     */
    String KEY_SECRET = "client_secret";
    /**
     * Response Key: code
     */
    String CODE = "code";
    /**
     * Response Key: data
     */
    String DATA = "data";
    /**
     * Response Key: msg
     */
    String MSG = "msg";

    /**
     * Create QiyClient in zero with vert.x style
     *
     * @param vertx
     * @return
     */
    static QiyClient createShared(final Vertx vertx) {
        return new QiyClientImpl(vertx);
    }

    /**
     * /iqiyi/authorize
     *
     * @param params
     * @param handler
     * @return
     */
    @Fluent
    QiyClient authorize(JsonObject params, Handler<AsyncResult<JsonObject>> handler);

    /**
     * @param handler
     * @return
     */
    @Fluent
    QiyClient authorize(Handler<AsyncResult<JsonObject>> handler);
}
