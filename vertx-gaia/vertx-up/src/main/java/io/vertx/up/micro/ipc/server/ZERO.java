package io.vertx.up.micro.ipc.server;

interface Info {

    String NODE_FINAL = "[ ZERO ] --> final node found, will provide response. method {0} of {1}";

    String NODE_MIDDLE = "[ ZERO ] --> middle node found, will transfer -->. method {0} of {1}";

    String NODE_RESPONSE = "[ ZERO ] -> Final response data = {0}";
}
