package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Types;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Stream for JsonObject
 */
public class Uson {

    private static final Annal LOGGER = Annal.get(Uson.class);

    private final transient JsonObject objectReference;

    public static Uson create(final String field, final Object value) {
        return new Uson(new JsonObject().put(field, value));
    }

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

    public Uson append(final String field, final Object value) {
        this.objectReference.put(field, value);
        return this;
    }

    public Uson convert(final String from, final String to) {
        Self.convert(this.objectReference, new ConcurrentHashMap<String, String>() {{
            this.put(from, to);
        }}, false);
        return this;
    }

    public Uson plus(final String from, final Integer seed) {
        final Object value = this.objectReference.getValue(from);
        if (null != value && Types.isInteger(value)) {
            final Integer old = this.objectReference.getInteger(from);
            this.objectReference.put(from, old + seed);
        }
        return this;
    }

    public <I, O> Uson convert(final String field, final Function<I, O> function) {
        Self.convert(this.objectReference, field, function, false);
        return this;
    }

    public Uson copy(final String from, final String to) {
        Self.copy(this.objectReference, from, to, false);
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
