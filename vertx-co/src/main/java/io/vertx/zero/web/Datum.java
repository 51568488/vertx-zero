package io.vertx.zero.web;

interface Info {

    String HTTP_SERVERS = "[ ZERO ] {0} (id = {1}) Agent has deployed HTTP Server on {2}.";

    String MAPPED_ROUTE = "[ ZERO ] ( Uri Register ) \"{1}\" has been deployed by {0}, Options = {2}.";

    String HTTP_LISTEN = "[ ZERO ] {0} Http Server has been started successfully. Endpoint: {1}.";

    String PLUGIN_LOAD = "[ ZERO ] ( Plugins ) Zero system detected the key = {0}, file = {1} " +
            "to read data: type = {2}, content = {3}.";
}
