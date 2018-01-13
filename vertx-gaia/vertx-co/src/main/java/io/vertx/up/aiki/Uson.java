package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Stream for JsonObject
 */
public class Uson {

    private transient JsonObject objectReference = new JsonObject();

    public static Uson create(final JsonObject item) {
        return new Uson(item);
    }

    private Uson(final JsonObject json) {
        this.objectReference = json;
    }

    public Uson append(final JsonObject object) {
        Dual.append(this.objectReference, object, false);
        return this;
    }

    public Uson append(final JsonArray array) {
        Dual.append(this.objectReference, array);
        return this;
    }

    public Uson convert(final String from, final String to) {
        Self.convert(this.objectReference, new ConcurrentHashMap<String, String>() {{
            this.put(from, to);
        }}, false);
        return this;
    }

    public Uson remove(final String... keys) {
        Self.remove(this.objectReference, false, keys);
        return this;
    }

    public Uson denull() {
        Self.deNull(this.objectReference, false);
        return this;
    }

    public JsonObject to() {
        return this.objectReference;
    }

    @Override
    public String toString() {
        return this.objectReference.encode();
    }
}
