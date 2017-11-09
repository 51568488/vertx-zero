package io.vertx.zero.core.equip;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.core.Transformer;
import org.vie.fun.HBool;

public class HttpServerStrada implements Transformer<HttpServerOptions> {

    @Override
    public HttpServerOptions transform(final JsonObject input) {
        final JsonObject config = input.getJsonObject(ServerVisitor.YKEY_CONFIG, null);
        return HBool.exec(null == config,
                HttpServerOptions::new,
                () -> new HttpServerOptions(config));
    }
}
