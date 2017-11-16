package io.vertx.up.rs.router;

import io.vertx.up.rs.Splitter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Info {

    String DISPATCH = "[ ZERO ] ( Mode = {0} ) Now dispatch the event to component {1}.";

    String NULL_EVENT = "[ ZERO ] ( {0} ) The system found \"null\" event in the queue. ";
}

interface Pool {
    ConcurrentMap<String, Splitter> THREADS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, Hub> URIHUBS
            = new ConcurrentHashMap<>();

    ConcurrentMap<String, Hub> MEDIAHUBS
            = new ConcurrentHashMap<>();
}
