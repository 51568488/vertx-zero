package io.vertx.zero.exception;

import io.vertx.core.json.JsonObject;

@Deprecated
public class RpcSslAlpnException extends UpException {

    public RpcSslAlpnException(final Class<?> clazz,
                               final Integer port,
                               final JsonObject options) {
        super(clazz, String.valueOf(port), options);
    }

    @Override
    public int getCode() {
        return -40036;
    }
}
