package io.vertx.up.cv;

public interface Info {
    String VTC_END = "[ZERO-AG] The {3} verticle {0} has been deployed " +
            "{1} instances successfully. id = {2}.";
    String VTC_FAIL = "[ZERO-AG] The {3} verticle {0} has been deployed " +
            "{1} instances failed. id = {2}, cause = {3}.";
    String AGENT_DEFINED = "[ZERO-AG] User defined agent {0} of type = {1}, " +
            "the default will be overwritten.";

    String SCANED_ENDPOINT = "[ZERO-SCN] The Zero system has found " +
            "{0} components of @EndPoint.";

    String SCANED_QUEUE = "[ZERO-SCN] The Zero system has found " +
            "{0} components of @Queue.";

    String SCANED_EVENTS = "[ZERO-SCN] The endpoint {0} scanned {1} events of Event, " +
            "will be mounted to routing system.";

    String SCANED_RECEIPTS = "[ZERO-SCN] The queue {0} scanned {1} records of Receipt, " +
            "will be mounted to event bus.";

    String APP_CLUSTERD = "[ZERO-APP] Current app is running in cluster mode, " +
            "manager = {0} on node {1} with status = {2}.";
}
