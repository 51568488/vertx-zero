package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Stream for JsonObject
 */
public class Blade {

    private transient JsonObject objectReference = new JsonObject();

    public static Blade create(final JsonObject item) {
        return new Blade(item);
    }

    private Blade(final JsonObject json) {
        this.objectReference = json;
    }

    public Blade append(final JsonObject object) {
        Dual.append(this.objectReference, object, false);
        return this;
    }

    public Blade append(final JsonArray array) {
        Dual.append(this.objectReference, array);
        return this;
    }

    public Blade remove(final String... keys) {
        Self.remove(this.objectReference, false, keys);
        return this;
    }

    public Blade denull() {
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
