package org.vie.util;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.vie.cv.Values;
import org.vie.fun.HBool;
import org.vie.fun.HNull;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

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

    private static final Set<Class<?>> SERIALIZED_CLS =
            new ConcurrentHashSet<Class<?>>() {
                {
                    add(int.class);
                    add(Integer.class);
                    add(short.class);
                    add(Short.class);
                    add(double.class);
                    add(Double.class);
                    add(BigDecimal.class);
                    add(long.class);
                    add(Long.class);
                    add(boolean.class);
                    add(Boolean.class);
                    add(float.class);
                    add(Float.class);
                    add(Date.class);
                    add(JsonObject.class);
                    add(JsonArray.class);
                    add(String.class);
                }
            };

    public static boolean isSerialized(final Object value) {
        if (null == value) {
            return false;
        }
        return SERIALIZED_CLS.contains(value.getClass());
    }

    public static boolean isArray(final Object value) {
        if (null == value) {
            return false;
        }
        return (value instanceof Collection ||
                value.getClass().isArray());
    }

    /**
     * Convert from string literal.
     *
     * @param paramType
     * @param literal
     * @return
     */
    public static Object fromString(final Class<?> paramType,
                                    final String literal) {
        final Object reference;
        if (int.class == paramType || Integer.class == paramType) {
            // int, Integer
            reference = Integer.parseInt(literal);

        } else if (short.class == paramType || Short.class == paramType) {
            // short, Short
            reference = Short.parseShort(literal);

        } else if (double.class == paramType || Double.class == paramType) {
            // double, Double
            reference = Double.parseDouble(literal);

        } else if (BigDecimal.class == paramType) {
            // BigDecimal
            reference = new BigDecimal(literal);

        } else if (long.class == paramType || Long.class == paramType) {
            // long, Long
            reference = Long.parseLong(literal);

        } else if (boolean.class == paramType || Boolean.class == paramType) {
            // boolean, Boolean
            reference = Boolean.valueOf(literal);

        } else if (float.class == paramType || Float.class == paramType) {
            // float, Short
            reference = Float.parseFloat(literal);

        } else if (Date.class == paramType) {
            // Date
            reference = Period.parse(literal);

        } else if (JsonObject.class == paramType) {
            // JsonObject
            reference = new JsonObject(literal);

        } else if (JsonArray.class == paramType) {
            // JsonArray
            reference = new JsonArray(literal);

        } else if (String.class == paramType) {
            // String
            reference = literal;

        } else if (StringBuilder.class == paramType) {
            // StringBuilder
            reference = new StringBuilder(literal);

        } else if (Buffer.class == paramType) {
            // Buffer
            final Buffer buffer = Buffer.buffer();
            buffer.appendBytes(literal.getBytes());
            reference = buffer;

        } else {
            reference = literal;
        }
        return reference;
    }
}
