package io.vertx.zero.atom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ClassDeserializer;
import com.fasterxml.jackson.databind.ClassSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Pojo metadata container
 * Meta of Java Object
 */
public class Mojo implements Serializable {

    private static final String TYPE = "type";
    private static final String MAPPING = "mapping";

    @JsonProperty(TYPE)
    @JsonSerialize(using = ClassSerializer.class)
    @JsonDeserialize(using = ClassDeserializer.class)
    private Class<?> type;

    @JsonProperty(MAPPING)
    private final ConcurrentMap<String, String> config = new ConcurrentHashMap<>();

    public Class<?> getType() {

        return this.type;
    }

    public void setType(final Class<?> type) {
        this.type = type;
    }

    public ConcurrentMap<String, String> getMapper() {
        return this.config;
    }

    public ConcurrentMap<String, String> getRevert() {
        final ConcurrentMap<String, String> mapper =
                new ConcurrentHashMap<>();
        this.config.forEach((key, value) -> mapper.put(value, key));
        return mapper;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mojo)) {
            return false;
        }
        final Mojo mojo = (Mojo) o;
        return Objects.equals(this.type, mojo.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.type);
    }
}
