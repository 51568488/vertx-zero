package io.vertx.up.rs.dispatcher;

import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Sentry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {
    ConcurrentMap<String, Aim> AIMS = new ConcurrentHashMap<>();

    ConcurrentMap<String, Sentry> SENTRIES = new ConcurrentHashMap<>();
}
