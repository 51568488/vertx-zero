package io.vertx.up.tool.container;

import java.util.Map;
import java.util.Objects;

public class KeyPair<K, V> {
    private K key;
    private V value;

    public static <K, V> KeyPair<K, V> create() {
        return new KeyPair<>(null, null);
    }

    public static <K, V> KeyPair<K, V> create(final K key,
                                              final V value) {
        return new KeyPair<>(key, value);
    }

    private KeyPair(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    public final K getKey() {
        return this.key;
    }

    public final V getValue() {
        return this.value;
    }

    public void set(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
    }

    @Override
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Map.Entry) {
            final Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return Objects.equals(this.key, e.getKey()) &&
                    Objects.equals(this.value, e.getValue());
        }
        return false;
    }
}
