package io.vertx.up.micro.ssl.client;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ClientOptionsBase;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.quiz.core.tls.Trust;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ssl.TrustPipe;

public class PemTrust implements TrustPipe<JsonObject> {

    private static final Annal LOGGER = Annal.get(PemTrust.class);

    private static final String PATH_CERT = "cert";

    @Override
    public Handler<ClientOptionsBase> parse(
            final JsonObject options) {
        return Fn.get(() -> {
            final PemTrustOptions pem = Fn.getSemi(
                    !options.containsKey(PATH_CERT), LOGGER,
                    Trust.CLIENT_PEM,
                    () -> new PemTrustOptions().addCertPath(PATH_CERT)
            );
            return option -> option
                    .setSsl(true)
                    .setUseAlpn(true)
                    .setPemTrustOptions(pem)
                    .setOpenSslEngineOptions(new OpenSSLEngineOptions());
        }, options);
    }
}
