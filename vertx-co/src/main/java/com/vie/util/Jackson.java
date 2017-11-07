package com.vie.util;

import com.vie.cv.Values;
import com.vie.hoc.HBool;
import com.vie.hoc.HNull;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Arrays;

/**
 * Lookup the json tree data
 */
@SuppressWarnings("unchecked")
public final class Jackson {

    public static JsonObject visitJObject(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, keys);
        return searchData(item, JsonObject.class, keys);
    }

    public static JsonArray visitJArray(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, keys);
        return searchData(item, JsonArray.class, keys);
    }

    public static Integer visitInt(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, keys);
        return searchData(item, Integer.class, keys);
    }

    public static String visitString(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, keys);
        return searchData(item, String.class, keys);
    }

    private static <T> T searchData(final JsonObject data,
                                    final Class<T> clazz,
                                    final String... pathes) {
        return HBool.exec(null == data || Values.ZERO == pathes.length,
                () -> null,
                () -> {
                    /** 1. Get current node  **/
                    final JsonObject current = data;
                    /** 2. Extract current input key **/
                    final String path = pathes[Values.IDX];
                    /** 3. Continue searching if key existing, otherwise terminal. **/
                    return HBool.exec(current.containsKey(path) && null != current.getValue(path),
                            () -> {
                                final Object curVal = current.getValue(path);
                                T result = null;
                                if (Values.ONE == pathes.length) {
                                    /** 3.1. Get the end node. **/
                                    if (clazz == curVal.getClass()) {
                                        result = (T) curVal;
                                    }
                                } else {
                                    /** 3.2. In the middle search **/
                                    if (Types.isJObject(curVal)) {
                                        final JsonObject continueNode = current.getJsonObject(path);
                                        /** 4.Extract new key **/
                                        final String[] continueKeys =
                                                Arrays.copyOfRange(pathes,
                                                        Values.ONE,
                                                        pathes.length);
                                        result = searchData(continueNode,
                                                clazz,
                                                continueKeys);
                                    }
                                }
                                return result;
                            },
                            () -> null);
                });

    }

    public static JsonArray toJArray(final Object value) {
        final JsonArray result = new JsonArray();
        HNull.exec(() -> {
            if (Types.isJArray(value)) {
                result.addAll((JsonArray) value);
            } else {
                result.add(value.toString());
            }
        }, value);
        return result;
    }

    private Jackson() {
    }
}
