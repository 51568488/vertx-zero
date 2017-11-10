package org.vie.util;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.vie.cv.Values;
import org.vie.fun.HBool;
import org.vie.fun.HNull;

import java.util.LinkedHashMap;

public class Types {

    public static <T extends Enum<T>> T fromStr(
            final Class<T> clazz,
            final String input) {
        return HNull.get(clazz, () -> Enum.valueOf(clazz, input), null);
    }

    public static boolean isJArray(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> value instanceof JsonArray);
    }

    public static boolean isJObject(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> value instanceof JsonObject ||
                        value instanceof LinkedHashMap);
    }

    public static boolean isInteger(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> Numeric.isInteger(value.toString()));
    }

    public static boolean isDecimal(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> Numeric.isDecimal(value.toString()));
    }

    public static boolean isBoolean(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> {
                    boolean logical = false;
                    final String literal = value.toString();
                    // Multi true literal such as "true", "TRUE" or 1
                    if (Values.TRUE.equalsIgnoreCase(literal)
                            || Integer.valueOf(1).toString().equalsIgnoreCase(literal)
                            || Values.FALSE.equalsIgnoreCase(literal)
                            || Integer.valueOf(0).toString().equalsIgnoreCase(literal)) {
                        logical = true;
                    }
                    return logical;
                });
    }

    public static boolean isDate(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> Period.isValid(value.toString()));
    }
}
