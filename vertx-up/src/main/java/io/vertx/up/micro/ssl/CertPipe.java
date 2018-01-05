package io.vertx.up.micro.ssl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.up.eon.em.CertType;
import io.vertx.up.micro.ssl.server.JksCert;
import io.vertx.up.micro.ssl.server.PemCert;
import io.vertx.up.micro.ssl.server.PfxCert;
import io.vertx.up.tool.mirror.Instance;

/**
 * Different ssl pipe
 *
 * @param <I>
 */
public interface CertPipe<I> {
    /**
     * Different pipe to parse JsonObject to generate Options
     *
     * @param options
     * @return
     */
    Handler<TCPSSLOptions> parse(I options);

    static CertPipe<JsonObject> get(final CertType type) {
        // 1. OpenSSL
        CertPipe<JsonObject> pipe = null;
        switch (type) {
            case PKCS12:
                pipe = Instance.singleton(PfxCert.class);
                break;
            case JKS:
                pipe = Instance.singleton(JksCert.class);
                break;
            case PEM:
                pipe = Instance.singleton(PemCert.class);
                break;
        }
        return pipe;
    }
}
