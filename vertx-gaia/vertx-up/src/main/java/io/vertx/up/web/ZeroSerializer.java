package io.vertx.up.web;

import io.reactivex.Observable;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.serialization.*;
import io.vertx.zero.atom.Mirror;
import io.vertx.zero.eon.Values;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * ZeroSerializer the request by different type.
 * 1. String -> T
 * 2. T -> JsonObject ( Envelop request )
 * 3. T -> String ( Generate resonse )
 * 4. Checking the request type to see where support serialization
 */
@SuppressWarnings("unchecked")
public class ZeroSerializer {

    private static final Annal LOGGER = Annal.get(ZeroSerializer.class);

    private static final ConcurrentMap<Class<?>, Saber> SABERS =
            new ConcurrentHashMap<Class<?>, Saber>() {
                {
                    this.put(int.class, Instance.singleton(IntegerSaber.class));
                    this.put(Integer.class, Instance.singleton(IntegerSaber.class));
                    this.put(short.class, Instance.singleton(ShortSaber.class));
                    this.put(Short.class, Instance.singleton(ShortSaber.class));
                    this.put(long.class, Instance.singleton(LongSaber.class));
                    this.put(Long.class, Instance.singleton(LongSaber.class));

                    this.put(double.class, Instance.singleton(DoubleSaber.class));
                    this.put(Double.class, Instance.singleton(DoubleSaber.class));

                    this.put(LocalDate.class, Instance.singleton(Java8DataTimeSaber.class));
                    this.put(LocalDateTime.class, Instance.singleton(Java8DataTimeSaber.class));
                    this.put(LocalTime.class, Instance.singleton(Java8DataTimeSaber.class));

                    this.put(float.class, Instance.singleton(FloatSaber.class));
                    this.put(Float.class, Instance.singleton(FloatSaber.class));
                    this.put(BigDecimal.class, Instance.singleton(BigDecimalSaber.class));

                    this.put(Enum.class, Instance.singleton(EnumSaber.class));

                    this.put(boolean.class, Instance.singleton(BooleanSaber.class));
                    this.put(Boolean.class, Instance.singleton(BooleanSaber.class));

                    this.put(Date.class, Instance.singleton(DateSaber.class));
                    this.put(Calendar.class, Instance.singleton(DateSaber.class));

                    this.put(JsonObject.class, Instance.singleton(JsonObjectSaber.class));
                    this.put(JsonArray.class, Instance.singleton(JsonArraySaber.class));

                    this.put(String.class, Instance.singleton(StringSaber.class));
                    this.put(StringBuffer.class, Instance.singleton(StringBufferSaber.class));
                    this.put(StringBuilder.class, Instance.singleton(StringBufferSaber.class));

                    this.put(Buffer.class, Instance.singleton(BufferSaber.class));
                    this.put(Set.class, Instance.singleton(CollectionSaber.class));
                    this.put(List.class, Instance.singleton(CollectionSaber.class));
                    this.put(Collection.class, Instance.singleton(CollectionSaber.class));

                    this.put(byte[].class, Instance.singleton(ByteArraySaber.class));
                    this.put(Byte[].class, Instance.singleton(ByteArraySaber.class));

                    this.put(File.class, Instance.singleton(FileSaber.class));
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

    @Deprecated
    public static <T> JsonArray toArray(final List<T> list, final Function<JsonObject, JsonObject> converted) {
        final JsonArray array = Jackson.serializeJson(list);
        final JsonArray result = new JsonArray();
        Observable.fromIterable(array)
                .filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(converted::apply)
                .subscribe(result::add);
        return result;
    }

    @Deprecated
    public static <T> JsonArray toArray(final List<T> list) {
        return toArray(list, item -> item);
    }

    @Deprecated
    public static <T> JsonArray toArray(final List<T> list, final String pojo) {
        return Fn.get(new JsonArray(), () -> {
            final Function<JsonObject, JsonObject> converted =
                    (from) -> Mirror.create(ZeroSerializer.class)
                            .mount(pojo).connect(from).to().result();
            return toArray(list, converted);
        }, pojo, list);
    }

    @Deprecated
    public static <T> JsonObject toObject(final T entity, final String pojo) {
        return Fn.get(new JsonObject(), () -> {
            final JsonObject from = Jackson.serializeJson(entity);
            return Mirror.create(ZeroSerializer.class)
                    .mount(pojo).connect(from).to().result();
        }, entity, pojo);
    }

    @Deprecated
    public static <T> Envelop collect(final List<T> list) {
        return Envelop.success(toArray(list, item -> item));
    }

    @Deprecated
    public static <T> Envelop unique(final List<T> list) {
        return Fn.getSemi(null == list || 0 == list.size(), LOGGER,
                () -> Envelop.success(new JsonObject()),
                () -> unique(list.get(Values.IDX)));
    }

    @Deprecated
    public static <T> Envelop unique(final JsonArray data) {
        return Fn.getSemi(null == data || 0 == data.size(), LOGGER,
                () -> Envelop.success(new JsonObject()),
                () -> unique(data.getJsonObject(Values.IDX)));
    }

    @Deprecated
    public static <T> Envelop unique(final T entity) {
        return Fn.getJvm(Envelop.success(new JsonObject()), () -> Envelop.success(entity), entity);
    }
}
