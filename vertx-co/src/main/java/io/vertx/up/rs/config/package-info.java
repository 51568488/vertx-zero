package io.vertx.up.rs.config;

interface Message {

    String AGENT_HIT = "[ZERO-AG] The standard verticle " +
            "{0} will be deployed.";

    String AGENT_OPT = "[ZERO-AG] The deployment options has been captured: " +
            "instances = {0}, group = {1}, ha = {2}, content = {3}";

    String PATH_NULL = "[ZERO-SCN] Input clazz = {0}, " +
            "it should not be null and annotated with javax.ws.rs.@Path.";
}
