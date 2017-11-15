package io.vertx.up.atom;

interface Info {
    /** **/
    String RULE_FILE = "[V] Rule file = {0} has been hitted. ";
    /** **/
    String RULE_CACHED_FILE = "[V] Rule file = {0}, read from memory directly.";
}

interface Key {
    String BRIEF = "brief";

    String STATUS = "status";

    String MESSAGE = "message";

    String CODE = "code";

    String DATA = "data";
}
