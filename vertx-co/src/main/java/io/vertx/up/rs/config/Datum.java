package io.vertx.up.rs.config;

interface Info {

    String AGENT_HIT = "[ZERO-AG] The standard verticle " +
            "{0} will be deployed.";

    String AGENT_OPT = "[ZERO-AG] The deployment options has been captured: " +
            "instances = {0}, group = {1}, ha = {2}, content = {3}";

    String METHOD_IGNORE = "[ZERO-SCN] Method name = {0} has not annotated with " +
            "javax.ws.rs.[@GET,@POST,@PUT,@DELETE,@OPTIONS,@PATCH,@HEAD], ignored resolving.";
}
