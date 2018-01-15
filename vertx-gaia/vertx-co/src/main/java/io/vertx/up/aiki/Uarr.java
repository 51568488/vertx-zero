package io.vertx.up.aiki;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Uarr {
    private static final Annal LOGGER = Annal.get(Uarr.class);

    private final transient JsonArray arrayReference;

    public static Uarr create(final JsonArray item) {
        return new Uarr(item);
    }

    private Uarr(final JsonArray jsonArray) {
        this.arrayReference = Fn.get(new JsonArray(), () -> jsonArray, jsonArray);
        LOGGER.info(Info.STREAM_START, String.valueOf(this.hashCode()), jsonArray);
    }

    public Uarr append(final JsonObject object) {
        this.arrayReference.add(object);
        return this;
    }

    public Uarr convert(final String from, final String to) {
        Self.convert(this.arrayReference, new ConcurrentHashMap<String, String>() {{
            this.put(from, to);
        }}, false);
        return this;
    }

    public <I, O> Uarr convert(final String field, final Function<I, O> function) {
        Self.convert(this.arrayReference, field, function, false);
        return this;
    }

    public Uarr copy(final String from, final String to) {
        Self.copy(this.arrayReference, from, to, false);
        return this;
    }

    public Uarr zip(final JsonArray array, final String fromKey, final String toKey) {
        Dual.zip(this.arrayReference, array, fromKey, toKey);
        return this;
    }

    public JsonArray to() {
        LOGGER.info(Info.STREAM_END, String.valueOf(this.hashCode()), this.arrayReference);
        return this.arrayReference;
    }

    @Override
    public String toString() {
        return this.arrayReference.encode();
    }
}
