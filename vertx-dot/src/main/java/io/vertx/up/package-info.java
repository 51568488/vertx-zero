package io.vertx.up;

interface Message {

    String AGENT_END = "[ZERO-AG] The standard verticle {0} has been deployed " +
            "{1} instances successfully. id = {2}.";
    String AGENT_FAIL = "[ZERO-AG] The standard verticle {0} has been deployed " +
            "{1} instances failed. id = {2}, cause = {3}.";
}
