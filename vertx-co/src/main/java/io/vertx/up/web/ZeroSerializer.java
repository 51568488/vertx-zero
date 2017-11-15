package io.vertx.up.web;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.web._400ParameterFromStringException;
import io.vertx.zero.func.HBool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.Jackson;
import io.vertx.zero.tool.Period;
import io.vertx.zero.tool.mirror.Instance;
import io.vertx.zero.tool.mirror.Types;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * ZeroSerializer the data by different type.
 * 1. String -> T
 * 2. T -> JsonObject ( Envelop data )
 * 3. T -> String ( Generate resonse )
 * 4. Checking the data type to see where support serialization
 */
public class ZeroSerializer {

    private static final Annal LOGGER = Annal.get(ZeroSerializer.class);

    private static void verifyInput(final boolean condition,
                                    final Class<?> paramType,
                                    final String literal) {
        HBool.execUp(condition,
                LOGGER, _400ParameterFromStringException.class,
                ZeroSerializer.class, paramType, literal);
    }

    /**
     * String -> T
     *
     * @param paramType
     * @param literal
     * @return
     */
    public static Object getValue(final Class<?> paramType,
                                  final String literal) {
        Object reference = null;
        if (null != literal) {
            if (int.class == paramType || Integer.class == paramType) {
                // int, Integer
                verifyInput(!Types.isInteger(literal), paramType, literal);
                reference = Integer.parseInt(literal);

            } else if (short.class == paramType || Short.class == paramType) {
                // short, Short
                verifyInput(!Types.isInteger(literal), paramType, literal);
                reference = Short.parseShort(literal);

            } else if (long.class == paramType || Long.class == paramType) {
                // long, Long
                verifyInput(!Types.isInteger(literal), paramType, literal);
                reference = Long.parseLong(literal);

            } else if (double.class == paramType || Double.class == paramType) {
                // double, Double
                verifyInput(!Types.isDecimal(literal), paramType, literal);
                reference = Double.parseDouble(literal);

            } else if (BigDecimal.class == paramType) {
                // BigDecimal
                verifyInput(!Types.isDecimal(literal), paramType, literal);
                reference = new BigDecimal(literal);

            } else if (float.class == paramType || Float.class == paramType) {
                // float, Short
                verifyInput(!Types.isDecimal(literal), paramType, literal);
                reference = Float.parseFloat(literal);

            } else if (boolean.class == paramType || Boolean.class == paramType) {
                // boolean, Boolean
                verifyInput(!Types.isBoolean(literal), paramType, literal);
                reference = Boolean.valueOf(literal);

            } else if (Date.class == paramType) {
                // Date
                verifyInput(!Types.isDate(literal), paramType, literal);
                reference = Period.parse(literal);

            } else if (JsonObject.class == paramType) {
                // JsonObject
                try {
                    reference = new JsonObject(literal);
                } catch (final DecodeException ex) {
                    throw new _400ParameterFromStringException(ZeroSerializer.class, paramType, literal);
                }
            } else if (JsonArray.class == paramType) {
                // JsonArray
                try {
                    reference = new JsonArray(literal);
                } catch (final DecodeException ex) {
                    throw new _400ParameterFromStringException(ZeroSerializer.class, paramType, literal);
                }
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
                // Other type, deserialize directly
                reference = Jackson.deserialize(literal, paramType);
            }
        }
        return reference;
    }

    private static final Set<Class<?>> SUPPORTED =
            new ConcurrentHashSet<Class<?>>() {
                {
                    add(int.class);
                    add(Integer.class);
                    add(short.class);
                    add(Short.class);
                    add(double.class);
                    add(Double.class);
                    add(long.class);
                    add(Long.class);
                    add(boolean.class);
                    add(Boolean.class);
                    add(float.class);
                    add(Float.class);
                    add(JsonObject.class);
                    add(JsonArray.class);
                    add(String.class);
                    add(byte[].class);
                    add(Byte[].class);
                }
            };

    /**
     * Check whether JsonObject supported
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> boolean isSupport(final T input) {
        if (null == input) {
            return true;
        }
        final Class<?> type = input.getClass();
        return SUPPORTED.contains(type);
    }

    /**
     * T -> JsonObject
     *
     * @param input
     * @param <T>
     * @return
     */
    public static <T> Object toSupport(final T input) {
        Object reference = null;
        if (isSupport(input)) {
            // Support type directly.
            reference = input;
        } else {
            // BigDecimal
            if (input instanceof BigDecimal) {
                final BigDecimal decimal = (BigDecimal) input;
                reference = decimal.doubleValue();
            }
            // Date
            if (input instanceof Date) {
                final Date date = (Date) input;
                reference = date.getTime();
            }
            // Time
            if (input instanceof Calendar) {
                final Calendar date = (Calendar) input;
                reference = date.getTime().getTime();
            }
            // StringBuffer, StringBuilder
            if (input instanceof StringBuffer ||
                    input instanceof StringBuilder) {
                reference = input.toString();
            }
            // Enum
            if (input instanceof Enum) {
                reference = Instance.invoke(input, "name");
            }
            // Null detect
            if (null == reference) {
                // Collection
                if (input instanceof Collection ||
                        input.getClass().isArray()) {
                    final String literal = Jackson.serialize(input);
                    reference = new JsonArray(literal);
                } else {
                    final String literal = Jackson.serialize(input);
                    reference = new JsonObject(literal);
                }
            }
        }
        return reference;
    }
}
