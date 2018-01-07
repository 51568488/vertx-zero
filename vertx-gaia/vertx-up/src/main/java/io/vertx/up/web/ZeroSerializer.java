package io.vertx.up.web;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.serialization.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * ZeroSerializer the request by different type.
 * 1. String -> T
 * 2. T -> JsonObject ( Envelop request )
 * 3. T -> String ( Generate resonse )
 * 4. Checking the request type to see where support serialization
 */
@SuppressWarnings("unchecked")
public class ZeroSerializer {

    private static final ConcurrentMap<Class<?>, Saber> SABERS =
            new ConcurrentHashMap<Class<?>, Saber>() {
                {
                    put(int.class, Instance.singleton(IntegerSaber.class));
                    put(Integer.class, Instance.singleton(IntegerSaber.class));
                    put(short.class, Instance.singleton(ShortSaber.class));
                    put(Short.class, Instance.singleton(ShortSaber.class));
                    put(long.class, Instance.singleton(LongSaber.class));
                    put(Long.class, Instance.singleton(LongSaber.class));

                    put(double.class, Instance.singleton(DoubleSaber.class));
                    put(Double.class, Instance.singleton(DoubleSaber.class));

                    put(LocalDate.class, Instance.singleton(Java8DataTimeSaber.class));
                    put(LocalDateTime.class, Instance.singleton(Java8DataTimeSaber.class));
                    put(LocalTime.class, Instance.singleton(Java8DataTimeSaber.class));

                    put(float.class, Instance.singleton(FloatSaber.class));
                    put(Float.class, Instance.singleton(FloatSaber.class));
                    put(BigDecimal.class, Instance.singleton(BigDecimalSaber.class));

                    put(Enum.class, Instance.singleton(EnumSaber.class));

                    put(boolean.class, Instance.singleton(BooleanSaber.class));
                    put(Boolean.class, Instance.singleton(BooleanSaber.class));

                    put(Date.class, Instance.singleton(DateSaber.class));
                    put(Calendar.class, Instance.singleton(DateSaber.class));

                    put(JsonObject.class, Instance.singleton(JsonObjectSaber.class));
                    put(JsonArray.class, Instance.singleton(JsonArraySaber.class));

                    put(String.class, Instance.singleton(StringSaber.class));
                    put(StringBuffer.class, Instance.singleton(StringBufferSaber.class));
                    put(StringBuilder.class, Instance.singleton(StringBufferSaber.class));

                    put(Buffer.class, Instance.singleton(BufferSaber.class));
                    put(Set.class, Instance.singleton(CollectionSaber.class));
                    put(List.class, Instance.singleton(CollectionSaber.class));
                    put(Collection.class, Instance.singleton(CollectionSaber.class));

                    put(byte[].class, Instance.singleton(ByteArraySaber.class));
                    put(Byte[].class, Instance.singleton(ByteArraySaber.class));
                }
            };

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
            Saber saber;
            if (paramType.isEnum()) {
                saber = SABERS.get(Enum.class);
            } else if (Collection.class.isAssignableFrom(paramType)) {
                saber = SABERS.get(Collection.class);
            } else {
                saber = SABERS.get(paramType);
            }
            if (null == saber) {
                saber = Instance.singleton(CommonSaber.class);
            }
            reference = saber.from(paramType, literal);
        }
        return reference;
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
        if (null != input) {
            Saber saber;
            final Class<?> cls = input.getClass();
            if (cls.isEnum()) {
                saber = SABERS.get(Enum.class);
            } else if (Calendar.class.isAssignableFrom(cls)) {
                saber = SABERS.get(Date.class);
            } else if (Collection.class.isAssignableFrom(cls)) {
                saber = SABERS.get(Collection.class);
            } else if (cls.isArray()) {
                final Class<?> type = cls.getComponentType();
                if (byte.class == type || Byte.class == type) {
                    saber = SABERS.get(byte[].class);
                } else {
                    saber = SABERS.get(Collection.class);
                }
            } else {
                saber = SABERS.get(cls);
            }
            if (null == saber) {
                saber = Instance.singleton(CommonSaber.class);
            }
            reference = saber.from(input);
        }
        return reference;
    }
}
