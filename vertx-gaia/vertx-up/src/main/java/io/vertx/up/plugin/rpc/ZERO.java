package io.vertx.up.plugin.rpc;

interface Info {

    String RPC_SERVICE = "[ ZERO ] ( Rpc Client ) Lookup service: name = {0}, addr = {1}";

    String CLIENT_BUILD = "[ ZERO ] Build channel for host = {0}, port = {1}";

    String CLIENT_RPC = "[ ZERO ] ( Rpc Client ) Build channel ( host = {0}, port = {1} )";
}

interface Key {
    String RULE_KEY = "rpcclient";
    String PATH = "path";
    String ADDR = "addr";
    String NAME = "name";
    String TYPE = "type";

    String SSL = "ssl";
    String HOST = "host";
    String PORT = "port";
}