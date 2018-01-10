package io.vertx.zero.atom;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {
    /** **/
    String RULE_FILE = "[V] Rule file = {0} has been hitted. ";
    /** **/
    String RULE_CACHED_FILE = "[V] Rule file = {0}, read from memory directly.";

    String VALUE_SAME = "[V] Warning for duplicated mapper size: keys = {0}, values = {1}";
}

interface Pool {

    ConcurrentMap<String, Mojo> MOJOS =
            new ConcurrentHashMap<>();
}
