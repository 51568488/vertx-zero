package io.vertx.up.micro;

import io.vertx.ext.web.Router;
import io.vertx.up.rs.Axis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String HTTP_SERVERS = "[ ZERO ] ( Http Server ) {0} (id = {1}) Agent has deployed HTTP Server on {2}.";

    String MAPPED_ROUTE = "[ ZERO ] ( Uri Register ) \"{1}\" has been deployed by {0}, Options = {2}.";

    String HTTP_LISTEN = "[ ZERO ] ( Http Server ) {0} Http Server has been started successfully. Endpoint: {1}.";

    String RPC_LISTEN = "[ ZERO ] ( Rpc Server ) Rpc Server has been started successfully. Channel: grpc://{0}:{1}. ";

    String RPC_FAILURE = "[ ZERO ] ( Rpc Server ) Rpc Server met failure: details = {0}.";
}

interface Pool {

    ConcurrentMap<String, Axis<Router>> ROUTERS = new ConcurrentHashMap<>();

    ConcurrentMap<String, Axis<Router>> EVENTS = new ConcurrentHashMap<>();
}
