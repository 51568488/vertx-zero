package io.vertx.zero.tool.mirror;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.eon.Values;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HNull;
import io.vertx.zero.tool.Numeric;
import io.vertx.zero.tool.Period;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Types {

    public static <T extends Enum<T>> T fromStr(
            final Class<T> clazz,
            final String input) {
        return HNull.get(clazz, () -> Enum.valueOf(clazz, input), null);
    }

    public static boolean isJArray(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> JsonArray.class == value.getClass());
    }

    public static boolean isJObject(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> JsonObject.class == value.getClass() ||
                        LinkedHashMap.class == value.getClass());
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

    private static final ObjectMapper YAML = new YAMLMapper();

    public static boolean isDate(final Object value) {
        return HBool.exec(null == value,
                () -> false,
                () -> Period.isValid(value.toString()));
    }

    public static boolean isArray(final Object value) {
        if (null == value) {
            return false;
        }
        return (value instanceof Collection ||
                value.getClass().isArray());
    }

    public static Class<?> toPrimary(final Class<?> source) {
        return UNBOXES.getOrDefault(source, source);
    }

    public static ConcurrentMap<Class<?>, Class<?>> UNBOXES =
            new ConcurrentHashMap<Class<?>, Class<?>>() {
                {
                    put(Integer.class, int.class);
                    put(Long.class, long.class);
                    put(Short.class, short.class);
                    put(Boolean.class, boolean.class);
                    put(Character.class, char.class);
                    put(Double.class, double.class);
                    put(Float.class, float.class);
                    put(Byte.class, byte.class);
                }
            };
}
