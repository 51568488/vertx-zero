package io.vertx.up.web.thread;

interface Info {
    String SCANED_EVENTS = "[ ZERO ] ( {1} Event ) The endpoint {0} scanned {1} events of Event, " +
            "will be mounted to routing system.";

    String SCANED_RECEIPTS = "[ ZERO ] ( {1} Receipt ) The queue {0} scanned {1} records of Receipt, " +
            "will be mounted to event bus.";
}
