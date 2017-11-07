package io.vertx.zero.ke.equip;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.ke.Transformer;

public class HttpServerStrada implements Transformer<HttpServerOptions> {

    @Override
    public HttpServerOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject("config", null);
        if (null != config) {
            return new HttpServerOptions(config);
        }

        return new HttpServerOptions();
    }
}
