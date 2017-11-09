package com.vie.fun;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class HPool {
    /**
     * Execute pool instance
     *
     * @param pool
     * @param key
     * @param poolFn
     * @param <T>
     * @return
     */
    public static <T> T exec(final ConcurrentMap<String, T> pool,
                             final String key,
                             final Supplier<T> poolFn) {
        return HBool.exec(null == pool || null == key,
                () -> null,
                () -> {
                    T reference = pool.get(key);
                    if (null == reference) {
                        reference = poolFn.get();
                        if (null != reference) {
                            pool.put(key, reference);
                        }
                    }
                    return reference;
                });
    }

    /**
     * Zipper ->
     * [{key,value},{key1,value1}]
     * -> key = value, key1 = value1
     *
     * @param object
     * @param keyFn
     * @param valueFn
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    public static <K, V, E> ConcurrentMap<K, V> zapper(
            final E[] object,
            final Function<E, K> keyFn,
            final Function<E, V> valueFn) {
        final ConcurrentMap<K, V> ret = new ConcurrentHashMap<>();
        if (0 < object.length) {
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
