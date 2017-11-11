package io.vertx.up.rs.config;

interface Info {

    String AGENT_HIT = "[ZERO-AG] The standard verticle " +
            "{0} will be deployed.";

    String WORKER_HIT = "[ZERO-WK] The worker verticl " +
            "{0} will be deployed.";

    String ADDRESS_IN = "[ZERO-WK] Vert.x zero has found {0} " +
            "incoming address from the system. Incoming address list as below: ";

    String ADDRESS_ITEM = "[ZERO-WK]        Addr : {0}";

    String VTC_OPT = "[ZERO-AG] The deployment options has been captured: " +
            "instances = {0}, group = {1}, ha = {2}, content = {3}";

    String METHOD_IGNORE = "[ZERO-SCN] Method name = {0} has not annotated with " +
            "javax.ws.rs.[@GET,@POST,@PUT,@DELETE,@OPTIONS,@PATCH,@HEAD], ignored resolving.";
}

interface Key {

    String TYPE = "type";

    String INSTANCES = "instances";

    String GROUP = "group";

    String HA = "ha";
}
