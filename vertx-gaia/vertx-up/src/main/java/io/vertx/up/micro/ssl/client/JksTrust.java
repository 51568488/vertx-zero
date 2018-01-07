package io.vertx.up.micro.ssl.client;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.up.micro.ssl.TrustPipe;

public class JksTrust implements TrustPipe<JsonObject> {

    @Override
    public Handler<ClientOptionsBase> parse(final JsonObject options) {
        return null;
    }
}
