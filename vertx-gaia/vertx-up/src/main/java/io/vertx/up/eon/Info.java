package io.vertx.up.eon;

public interface Info {
    String VTC_END = "[ ZERO ] ( {3} ) The verticle {0} has been deployed " +
            "{1} instances successfully. id = {2}.";
    String VTC_FAIL = "[ ZERO ] ( {3} ) The verticle {0} has been deployed " +
            "{1} instances failed. id = {2}, cause = {3}.";

    String VTC_STOPPED = "[ ZERO ] ( {2} ) The verticle {0} has been undeployed " +
            " successfully, id = {1}.";

    String AGENT_DEFINED = "[ ZERO ] User defined agent {0} of type = {1}, " +
            "the default will be overwritten.";

    String SCANED_ENDPOINT = "[ ZERO ] ( {0} EndPoint ) The Zero system has found " +
            "{0} components of @EndPoint.";

    String SCANED_QUEUE = "[ ZERO ] ( {0} Queue ) The Zero system has found " +
            "{0} components of @Queue.";

    String SCANED_INJECTION = "[ ZERO ] ( {1} Inject ) The Zero system has found \"{0}\" object contains " +
            "{1} components of @Inject or ( javax.inject.infix.* ).";

    String APP_CLUSTERD = "[ ZERO ] Current app is running in cluster mode, " +
            "manager = {0} on node {1} with status = {2}.";

    String SOCK_ENABLED = "[ ZERO ] ( Micro -> Sock ) Zero system detected the socket server is Enabled.";

    String RPC_ENABLED = "[ ZERO ] ( Micro -> Rpc ) Zero system detected the rpc server is Enabled. ";
}
