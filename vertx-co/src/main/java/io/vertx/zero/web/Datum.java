package io.vertx.zero.web;

interface Info {

    String HTTP_SERVERS = "[ ZERO ] {0} (id = {1}) Agent has deployed HTTP Server on {2}.";

    String MAPPED_ROUTE = "[ ZERO ] ( Uri Register ) \"{1}\" has been deployed by {0}, Options = {2}.";

    String HTTP_LISTEN = "[ ZERO ] {0} Http Server has been started successfully. Endpoint: {1}.";
}
