package io.vertx.up.micro.ssl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.up.eon.em.CertType;
import io.vertx.up.micro.ssl.client.JksTrust;
import io.vertx.up.micro.ssl.client.PemTrust;
import io.vertx.up.micro.ssl.client.PfxTrust;
import io.vertx.up.tool.mirror.Instance;

public interface TrustPipe<I> {

    Handler<ClientOptionsBase> parse(I options);

    static TrustPipe<JsonObject> get(final CertType type) {
        // 1. OpenSSL
        TrustPipe<JsonObject> pipe = null;
        switch (type) {
            case PKCS12:
                pipe = Instance.singleton(PfxTrust.class);
                break;
            case JKS:
                pipe = Instance.singleton(JksTrust.class);
                break;
            case PEM:
                pipe = Instance.singleton(PemTrust.class);
                break;
        }
        return pipe;
    }
}
