package io.vertx.up.rs.announce;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

interface Pool {

    ConcurrentMap<Class<?>, Rigor> RIGORS = new ConcurrentHashMap<Class<?>, Rigor>() {
        {
            this.put(JsonObject.class, new JObjectRigor());
            this.put(JsonArray.class, new JArrayRigor());
            this.put(File.class, new FileRigor());
        }
    };
}
