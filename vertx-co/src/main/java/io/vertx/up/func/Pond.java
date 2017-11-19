package io.vertx.up.func;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;

class Pond {

    /**
     * Enhancement
     *
     * @param pool
     * @param key
     * @param poolFn
     * @param <K>
     * @param <V>
     * @return
     */
    static <K, V> V exec(final ConcurrentMap<K, V> pool,
                         final K key,
                         final Supplier<V> poolFn) {
        V reference = pool.get(key);
        if (null == reference) {
            reference = poolFn.get();
            if (null != reference) {
                pool.put(key, reference);
            }
        }
        return reference;
    }

    /**
     * Group function
     *
     * @param object
     * @param keyFn
     * @param valueFn
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    static <K, V, E> ConcurrentMap<K, List<V>> group(
            final Collection<E> object,
            final Function<E, K> keyFn,
            final Function<E, V> valueFn
    ) {
        final ConcurrentMap<K, List<V>> ret = new ConcurrentHashMap<>();
        if (0 < object.size()) {
            for (final E item : object) {
                if (null != item) {
                    final K key = keyFn.apply(item);
                    if (null != key) {
                        // Extract List
                        List<V> reference = null;
                        if (ret.containsKey(key)) {
                            reference = ret.get(key);
                        }
                        // Double check
                        if (null == reference) {
                            reference = new ArrayList<>();
                        }
                        final V value = valueFn.apply(item);
                        if (null != value) {
                            reference.add(value);
                        }
                        // Replace finally
                        ret.put(key, reference);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Zipper
     *
     * @param object
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    static <K, V, E> ConcurrentMap<K, V> zipper(
            final Collection<E> object,
            final Function<E, K> keyFn,
            final Function<E, V> valueFn
    ) {
        final ConcurrentMap<K, V> ret = new ConcurrentHashMap<>();
        if (0 < object.size()) {
            for (final E item : object) {
                if (null != item) {
                    final K key = keyFn.apply(item);
                    final V value = valueFn.apply(item);
                    if (null != key && null != value) {
                        ret.put(key, value);
                    }
                }
            }
        }
        return ret;
    }
}
