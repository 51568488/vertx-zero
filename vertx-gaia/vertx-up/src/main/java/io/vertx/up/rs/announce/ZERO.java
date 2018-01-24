package io.vertx.up.rs.announce;

import io.vertx.core.json.JsonObject;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {

    ConcurrentMap<Class<?>, Rigor> RIGORS = new ConcurrentHashMap<Class<?>, Rigor>() {
        {
            this.put(JsonObject.class, new JObjectRigor());
        }
    };
}
