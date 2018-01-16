package io.vertx.up.tool;

import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Do some specification statute operations
 */
public final class Statute {

    private static final Annal LOGGER = Annal.get(Statute.class);

    /**
     * @param list     The target list
     * @param fnFilter the filter for list search.
     * @param <T>      The generic type of list element.
     * @return Found type for target generic type.
     */
    public static <T> T findUnique(final List<T> list, final Predicate<T> fnFilter) {
        return Fn.get(() -> {
            final List<T> filtered = list.stream()
                    .filter(fnFilter).collect(Collectors.toList());
            return Fn.getSemi(filtered.isEmpty(), LOGGER,
                    Fn::nil,
                    () -> filtered.get(Values.IDX));
        }, list, fnFilter);
    }

    /**
     * Merge two map
     *
     * @param from
     * @param to
     * @param <K>
     * @param <T>
     * @param <V>
     * @return
     */
    public static <K, T, V> ConcurrentMap<K, V> reduce(
            final ConcurrentMap<K, T> from,
            final ConcurrentMap<T, V> to) {
        final ConcurrentMap<K, V> result = new ConcurrentHashMap<>();
        from.forEach((key, middle) -> {
            final V value = to.get(middle);
            if (null != value) {
                result.put(key, value);
            }
        });
        return result;
    }

    /**
     * @param from
     * @param to
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> ConcurrentMap<K, V> reduce(
            final Set<K> from,
            final ConcurrentMap<K, V> to
    ) {
        final ConcurrentMap<K, V> result = new ConcurrentHashMap<>();
        from.forEach((key) -> {
            final V value = to.get(key);
            if (null != value) {
                result.put(key, value);
            }
        });
        return result;
    }

    /**
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> ConcurrentMap<F, T> zipper(
            final List<F> keys,
            final List<T> values
    ) {
        final ConcurrentMap<F, T> result = new ConcurrentHashMap<>();
        Fn.itList(keys, (key, index) -> {
            final T value = getEnsure(values, index);
            // Ignore for null element
            if (null != key && null != value) {
                result.put(key, value);
            }
        });
        return result;
    }

    /**
     * @param first
     * @param second
     * @param function
     * @param <F>
     * @param <S>
     * @param <T>
     * @return
     */
    public static <F, S, T> List<T> zipper(
            final List<F> first,
            final List<S> second,
            final BiFunction<F, S, T> function
    ) {
        final List<T> result = new ArrayList<>();
        Fn.itList(first, (key, index) -> {
            final S value = getEnsure(second, index);
            final T merged = function.apply(key, value);
            if (null != merged) {
                result.add(merged);
            }
        });
        return result;
    }

    private static <T> T getEnsure(final List<T> list, final int index) {
        return (0 <= index) && (index < list.size()) ? list.get(index) : null;
    }

    private Statute() {
    }
}
