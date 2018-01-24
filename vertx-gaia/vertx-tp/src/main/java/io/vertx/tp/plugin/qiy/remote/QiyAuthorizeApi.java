package io.vertx.tp.plugin.qiy.remote;

import feign.Param;
import feign.RequestLine;
import io.vertx.core.json.JsonObject;

public interface QiyAuthorizeApi {

    @RequestLine("GET /iqiyi/authorize?client_id={client_id}&client_secret={client_secret}")
    JsonObject authorize(@Param("client_id") String clientId,
                         @Param("client_secret") String clientSecret);
}
