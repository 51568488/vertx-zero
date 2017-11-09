package com.vie.fun;

import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

public final class HMap {

    public static <K, V> void exec(final ConcurrentMap<K, V> map,
                                   final BiConsumer<K, V> fnEach) {
        map.forEach((key, value) -> {
            if (null != key && null != value) {
                fnEach.accept(key, value);
            }
        });
    }

    private HMap() {
    }
}
