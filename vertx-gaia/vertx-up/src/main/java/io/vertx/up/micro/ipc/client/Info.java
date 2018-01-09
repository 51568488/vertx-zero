package io.vertx.up.micro.ipc.client;

interface Info {

    String RECORD_FOUND = "[ ZERO ] Find matched record {0}";

    String CLIENT_BUILD = "[ ZERO ] Build channel for host = {0}, port = {1}";

    String CLIENT_RPC = "[ ZERO ] ( Rpc Client ) Build channel ( host = {0}, port = {1} )";
}
