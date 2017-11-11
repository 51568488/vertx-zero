package io.vertx.up.web;

interface Info {

    String HTTP_SERVERS = "[ZERO-VTC] {0} (id = {1}) Agent has deployed HTTP Server on {2}.";

    String MAPPED_ROUTE = "[ZERO-VTC] ( URI MAPPING ) \"{1}\" has been deployed by {0}, Options = {2}.";

    String HTTP_LISTEN = "[ZERO-VTC] {0} Http Server has been started successfully. Endpoint: {1}.";
}
