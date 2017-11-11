package io.vertx.up.rs.router;

interface Message {

    String DISPATCH = "[ZERO-RQ] ( Mode = {0} ) Now dispatch the event to component {1}.";

    String NULL_EVENT = "[ZERO-RQ] ( {0} ) The system found \"null\" event in the queue. ";
}
