package io.vertx.up.micro.ssl.server;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.TCPSSLOptions;
import io.vertx.quiz.core.tls.Cert;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ssl.CertPipe;

/**
 * Pem key cert options
 */
@SuppressWarnings("unchecked")
public class PemCert implements CertPipe<JsonObject> {

    private static final Annal LOGGER = Annal.get(PemCert.class);

    private static final String PATH_CERT = "cert";
    private static final String PATH_KEY = "key";

    @Override
    public Handler<TCPSSLOptions> parse(final JsonObject options) {
        return Fn.get(() -> {
            final PemKeyCertOptions pem = Fn.getSemi(
                    null == options ||
                            !options.containsKey(PATH_KEY) ||
                            !options.containsKey(PATH_CERT), LOGGER,
                    Cert.SERVER_PEM,
                    () -> new PemKeyCertOptions().setKeyPath(PATH_KEY).setCertPath(PATH_CERT)
            );
            return option -> option
                    .setSsl(true)
                    .setUseAlpn(true)
                    .setPemKeyCertOptions(pem)
                    .setOpenSslEngineOptions(new OpenSSLEngineOptions());
        }, options);
    }
}
