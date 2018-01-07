package io.vertx.up.rs.dispatch;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.rs.Aim;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<String, Aim<RoutingContext>> AIMS = new ConcurrentHashMap<>();
}
