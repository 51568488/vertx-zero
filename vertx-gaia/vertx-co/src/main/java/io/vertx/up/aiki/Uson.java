package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Stream for JsonObject
 */
public class Uson {

    private static final Annal LOGGER = Annal.get(Uson.class);

    private final transient JsonObject objectReference;

    public static Uson create(final JsonObject item) {
        return new Uson(item);
    }

    private Uson(final JsonObject json) {
        this.objectReference = Fn.get(new JsonObject(), () -> json, json);
        LOGGER.info(Info.STREAM_START, String.valueOf(this.hashCode()), json);
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
        LOGGER.info(Info.STREAM_END, String.valueOf(this.hashCode()), this.objectReference);
        return this.objectReference;
    }

    @Override
    public String toString() {
        return this.objectReference.encode();
    }
}
