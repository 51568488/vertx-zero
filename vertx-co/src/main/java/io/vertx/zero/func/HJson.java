package io.vertx.zero.func;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.func.error.JdBiConsumer;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

@SuppressWarnings("unchecked")
public class HJson {

    public static <T> void execIt(final JsonObject data,
                                  final BiConsumer<T, String> fnIt) {
        HNull.exec(() -> {
            for (final String name : data.fieldNames()) {
                final Object item = data.getValue(name);
                if (null != item) {
                    /**
                     * Swap the value and key.
                     */
                    fnIt.accept((T) item, name);
                }
            }
        }, data, fnIt);
    }

    public static void execIt(final JsonArray dataArray,
                              final BiConsumer<JsonObject, Integer> fnIt) {
        HNull.exec(() -> {
            final int size = dataArray.size();
            for (int idx = Values.IDX; idx < size; idx++) {
                final Object value = dataArray.getValue(idx);
                if (null != value) {
                    if (JsonObject.class == value.getClass()
                            || LinkedHashMap.class == value.getClass()) {
                        final JsonObject item = (JsonObject) value;
                        fnIt.accept(item, idx);
                    }
                }
            }
        }, dataArray, fnIt);
    }

    public static <T> void execZero(final JsonObject data,
                                    final JdBiConsumer<T, String> fnIt)
            throws ZeroException {
        HNull.execZero(() -> {
            for (final String name : data.fieldNames()) {
                final Object item = data.getValue(name);
                if (null != item) {
                    fnIt.accept((T) item, name);
                }
            }
        }, data, fnIt);
    }

    public static <T> void execZero(final JsonArray dataArray,
                                    final Class<T> clazz,
                                    final JdBiConsumer<T, Integer> fnIt)
            throws ZeroException {
        HNull.execZero(() -> {
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
        }, dataArray, clazz, fnIt);
    }
}
