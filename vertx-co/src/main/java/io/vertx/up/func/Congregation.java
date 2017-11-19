package io.vertx.up.func;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.ZeroException;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

/**
 * For collection
 */
@SuppressWarnings("unchecked")
class Congregation {

    private static final Annal LOGGER = Annal.get(Congregation.class);

    /**
     * @param map
     * @param fnEach
     * @param <K>
     * @param <V>
     */
    static <K, V> void exec(final ConcurrentMap<K, V> map,
                            final BiConsumer<K, V> fnEach) {
        map.forEach((key, value) -> {
            if (null != key && null != value) {
                fnEach.accept(key, value);
            }
        });
    }

    /**
     * @param list
     * @param fnEach
     * @param <V>
     */
    static <V> void exec(final List<V> list,
                         final BiConsumer<V, Integer> fnEach) {
        final int size = list.size();
        for (int idx = Values.IDX; idx < size; idx++) {
            final V item = list.get(idx);
            if (null != item) {
                fnEach.accept(item, idx);
            }
        }
    }

    /**
     * @param data
     * @param fnEach
     * @param <T>
     */
    static <T> void exec(final JsonObject data,
                         final BiConsumer<T, String> fnEach) {
        try {
            execZero(data, fnEach::accept);
        } catch (final ZeroException ex) {
            LOGGER.jvm(ex);
        }
    }

    static <T> void execZero(final JsonObject data,
                             final ZeroBiConsumer<T, String> fnIt)
            throws ZeroException {
        for (final String name : data.fieldNames()) {
            final Object item = data.getValue(name);
            if (null != item) {
                fnIt.accept((T) item, name);
            }
        }
    }

    /**
     * @param dataArray
     * @param clazz
     * @param fnEach
     * @param <T>
     */
    static <T> void exec(final JsonArray dataArray,
                         final Class<T> clazz,
                         final BiConsumer<T, Integer> fnEach) {
        try {
            execZero(dataArray, clazz, fnEach::accept);
        } catch (final ZeroException ex) {
            LOGGER.jvm(ex);
        }
    }

    /**
     * @param dataArray
     * @param fnIt
     * @param <T>
     */
    static <T> void execZero(final JsonArray dataArray,
                             final ZeroBiConsumer<T, String> fnIt)
            throws ZeroException {
        execZero(dataArray, JsonObject.class, (element, index) -> {
            execZero(element, fnIt::accept);
        });
    }

    /**
     * @param dataArray
     * @param clazz
     * @param fnIt
     * @param <T>
     * @throws ZeroException
     */
    static <T> void execZero(final JsonArray dataArray,
                             final Class<T> clazz,
                             final ZeroBiConsumer<T, Integer> fnIt)
            throws ZeroException {
        final int size = dataArray.size();
        for (int idx = Values.IDX; idx < size; idx++) {
            final Object value = dataArray.getValue(idx);
            if (null != value) {
                if (clazz == value.getClass()) {
                    final T item = (T) value;
                    fnIt.accept(item, idx);
                }
            }
        }
    }
}
