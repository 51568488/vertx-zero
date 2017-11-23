package io.vertx.up.micro;

import io.vertx.up.rs.Axis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String HTTP_SERVERS = "[ ZERO ] ( Http Server ) {0} (id = {1}) Agent has deployed HTTP Server on {2}.";

    String MAPPED_ROUTE = "[ ZERO ] ( Uri Register ) \"{1}\" has been deployed by {0}, Options = {2}.";

    String HTTP_LISTEN = "[ ZERO ] ( Http Server ) {0} Http Server has been started successfully. Endpoint: {1}.";
}

interface Pool {

    ConcurrentMap<String, Axis> ROUTERS = new ConcurrentHashMap<>();

    ConcurrentMap<String, Axis> EVENTS = new ConcurrentHashMap<>();
}
