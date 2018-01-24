package io.vertx.tp.plugin.qiy;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.feign.FeignDepot;
import io.vertx.tp.plugin.qiy.remote.QiyAuthorizeApi;
import io.vertx.up.exception.QiyAuthorizedException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Ruler;

public class QiyClientImpl implements QiyClient {

    private static final Annal LOGGER = Annal.get(QiyClientImpl.class);

    private static final FeignDepot DEPOT = FeignDepot.create("qiy");
    private static final String KEY = "qiy";

    private transient final Vertx vertx;
    private transient final String clientId;
    private transient final String clientSecret;

    private transient final QiyAuthorizeApi authorizeApi;

    public QiyClientImpl(final Vertx vertx) {
        this.vertx = vertx;
        // Verify the data
        final JsonObject config = DEPOT.getConfig();
        this.verifyConfig(config);
        this.clientId = config.getString(KEY_ID);
        this.clientSecret = config.getString(KEY_SECRET);
        // Authorized Api Reference
        this.authorizeApi = DEPOT.build(QiyAuthorizeApi.class);
    }

    @Override
    public QiyClient authorize(final JsonObject params,
                               final Handler<AsyncResult<JsonObject>> handler) {
        this.verifyConfig(params);
        final String clientId = params.getString(KEY_ID);
        final String clientSecret = params.getString(KEY_SECRET);
        final JsonObject data = this.authorizeApi.authorize(clientId, clientSecret);
        this.buildResponse(data, handler);
        return this;
    }

    @Override
    public QiyClient authorize(final Handler<AsyncResult<JsonObject>> handler) {
        final JsonObject data = this.authorizeApi.authorize(this.clientId, this.clientSecret);
        this.buildResponse(data, handler);
        return this;
    }

    private void verifyConfig(final JsonObject config) {
        Fn.flingUp(() -> Fn.shuntZero(() -> Ruler.verify(KEY, config), config),
                LOGGER);
    }

    private void buildResponse(final JsonObject response,
                               final Handler<AsyncResult<JsonObject>> handler) {
        LOGGER.info(Info.FEIGN_RESPONSE, response);
        if (QiyCodes.SUCCESS.equals(response.getString(CODE))) {
            handler.handle(Future.succeededFuture(response.getJsonObject(DATA)));
        } else {
            handler.handle(Future.failedFuture(new QiyAuthorizedException(this.getClass(),
                    response.getString(CODE), response.getString(MSG))));
        }
    }
}
