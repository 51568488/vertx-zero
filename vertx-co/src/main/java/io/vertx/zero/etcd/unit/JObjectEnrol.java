package io.vertx.zero.etcd.unit;

import com.google.gson.JsonObject;
import io.vertx.zero.etcd.Enrol;

public class JObjectEnrol implements Enrol<JsonObject> {
    
    @Override
    public boolean write(final String path,
                         final JsonObject entity) {
        return false;
    }

    @Override
    public JsonObject read(final String path) {
        return null;
    }
}
