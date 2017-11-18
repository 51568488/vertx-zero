package io.vertx.zero.func;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

public final class HMulti {

    public static <K, V> void exec(final ConcurrentMap<K, V> map,
                                   final BiConsumer<K, V> fnEach) {
        map.forEach((key, value) -> {
            if (null != key && null != value) {
                fnEach.accept(key, value);
            }
        });
    }

    public static <V> void exec(final List<V> collection,
                                final BiConsumer<V, Integer> fnEach) {
        final int size = collection.size();
        for (int idx = 0; idx < size; idx++) {
            final V item = collection.get(idx);
            if (null != item) {
                fnEach.accept(item, idx);
            }
        }
    }

    public static <V> void exec(final Set<V> collection,
                                final BiConsumer<V, Integer> fnEach) {
        final List<V> list = new ArrayList<>(collection);
        exec(list, fnEach);
    }

    private HMulti() {
    }
}
