package io.vertx.rx.rs.router;

import io.vertx.reactivex.ext.web.Route;
import io.vertx.up.rs.Sentry;
import io.vertx.up.rs.dispatcher.ModeSplitter;
import io.vertx.up.rs.router.Hub;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {
    String NULL_EVENT = "[ ZERO ] ( {0} ) The system found \"null\" event in the queue. ";
}

interface Pool {
    ConcurrentMap<String, ModeSplitter> THREADS
            = new ConcurrentHashMap<>();
    ConcurrentMap<String, Sentry> VERIFIERS
            = new ConcurrentHashMap<>();
    
    ConcurrentMap<String, Hub<Route>> URIHUBS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, Hub<Route>> MEDIAHUBS
            = new ConcurrentHashMap<>();
}
