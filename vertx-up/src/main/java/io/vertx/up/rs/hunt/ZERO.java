package io.vertx.up.rs.hunt;

import io.vertx.up.rs.Aim;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String PARAM_FLOW = "[ ZERO ] Container start to scan parameters: type = {0}.";
}

interface Pool {

    ConcurrentMap<String, Aim> AIMS = new ConcurrentHashMap<>();
}
