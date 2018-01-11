package io.vertx.up.tool;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.reactivex.Observable;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.aiki.Blade;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.eon.Values;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.function.Supplier;

/**
 * Lookup the json tree data
 */
@SuppressWarnings({"unchecked"})
public final class Jackson {

    private static final Annal LOGGER = Annal.get(Jackson.class);
    public static ObjectMapper MAPPER = new ObjectMapper();

    static {
        // TimeZone Issue
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Jackson.MAPPER.setDateFormat(dateFormat);
        // Ignore null value
        Jackson.MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Non-standard JSON but we allow C style comments in our JSON
        Jackson.MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        Jackson.MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        Jackson.MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Jackson.MAPPER.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

        final SimpleModule module = new SimpleModule();
        // custom types
        module.addSerializer(JsonObject.class, new JsonObjectSerializer());
        module.addSerializer(JsonArray.class, new JsonArraySerializer());
        // he have 2 extensions: RFC-7493
        module.addSerializer(Instant.class, new InstantSerializer());
        module.addSerializer(byte[].class, new ByteArraySerializer());
        // Zero Extension
        module.addSerializer(Blade.class, new BladeSerializer());
        module.addDeserializer(Blade.class, new BladeDeserializer());

        Jackson.MAPPER.registerModule(module);
        Jackson.MAPPER.findAndRegisterModules();
    }

    public static JsonObject visitJObject(
            final JsonObject item,
            final String... keys
    ) {

        Ensurer.gtLength(Jackson.class, 0, (Object[]) keys);
        return Jackson.searchData(item, JsonObject.class, keys);
    }

    public static JsonArray visitJArray(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, (Object[]) keys);
        return Jackson.searchData(item, JsonArray.class, keys);
    }

    public static Integer visitInt(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, (Object[]) keys);
        return Jackson.searchData(item, Integer.class, keys);
    }

    public static String visitString(
            final JsonObject item,
            final String... keys
    ) {
        Ensurer.gtLength(Jackson.class, 0, (Object[]) keys);
        return Jackson.searchData(item, String.class, keys);
    }

    private static <T> T searchData(final JsonObject data,
                                    final Class<T> clazz,
                                    final String... pathes) {
        if (null == data || Values.ZERO == pathes.length) {
            return null;
        }/** 1. Get current node  **/
        final JsonObject current = data;
        /** 2. Extract current input key **/
        final String path = pathes[Values.IDX];
        /** 3. Continue searching if key existing, otherwise terminal. **/
        return Fn.getSemi(current.containsKey(path) && null != current.getValue(path),
                Jackson.LOGGER,
                () -> {
                    final Object curVal = current.getValue(path);
                    T result = null;
                    if (Values.ONE == pathes.length) {
                        /** 3.1. Get the end node. **/
                        if (clazz == curVal.getClass()) {
                            result = (T) curVal;
                        }
                    } else {
                        /** 3.2. Address the middle search **/
                        if (Types.isJObject(curVal)) {
                            final JsonObject continueNode = current.getJsonObject(path);
                            /** 4.Extract new key **/
                            final String[] continueKeys =
                                    Arrays.copyOfRange(pathes,
                                            Values.ONE,
                                            pathes.length);
                            result = Jackson.searchData(continueNode,
                                    clazz,
                                    continueKeys);
                        }
                    }
                    return result;
                },
                Fn::nil);
    }

    public static JsonArray mergeZip(final JsonArray source, final JsonArray target,
                                     final String sourceKey, final String targetKey) {
        final JsonArray result = new JsonArray();
        Fn.safeJvm(() -> Observable.fromIterable(source)
                .filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .map(item -> item.mergeIn(Jackson.findByKey(target, targetKey, item.getValue(sourceKey))))
                .subscribe(result::add), Jackson.LOGGER);
        return result;
    }

    private static JsonObject findByKey(final JsonArray source,
                                        final String key,
                                        final Object value) {
        return Fn.getJvm(() -> Observable.fromIterable(source)
                .filter(Objects::nonNull)
                .map(item -> (JsonObject) item)
                .filter(item -> null != item.getValue(key))
                .filter(item -> value == item.getValue(key) || item.getValue(key).equals(value))
                .first(new JsonObject()).blockingGet(), source, key);
    }

    public static JsonObject validJObject(final Supplier<JsonObject> supplier) {
        JsonObject result;
        try {
            result = supplier.get();
        } catch (final DecodeException ex) {
            result = new JsonObject();
        }
        return result;
    }

    public static JsonArray validJArray(final Supplier<JsonArray> supplier) {
        JsonArray result;
        try {
            result = supplier.get();
        } catch (final DecodeException ex) {
            result = new JsonArray();
        }
        return result;
    }

    public static JsonArray toJArray(final Object value) {
        final JsonArray result = new JsonArray();
        Fn.safeNull(() -> {
            if (Types.isJArray(value)) {
                result.addAll((JsonArray) value);
            } else {
                result.add(value.toString());
            }
        }, value);
        return result;
    }

    public static <T, R extends Iterable> R serializeJson(final T t) {
        final String content = Jackson.serialize(t);
        return Fn.getJvm(null,
                () -> Fn.getSemi(content.trim().startsWith(Strings.LEFT_BRACES), Jackson.LOGGER,
                        () -> (R) new JsonObject(content),
                        () -> (R) new JsonArray(content)), content);
    }

    public static <T> String serialize(final T t) {
        return Fn.get(null, () -> Fn.getJvm(() -> Jackson.MAPPER.writeValueAsString(t), t), t);
    }


    public static <T> T deserialize(final JsonObject value, final Class<T> type) {
        return Fn.get(null,
                () -> Jackson.deserialize(value.encode(), type), value);
    }

    public static <T> T deserialize(final JsonArray value, final Class<T> type) {
        return Fn.get(null,
                () -> Jackson.deserialize(value.encode(), type), value);
    }

    public static <T> T deserialize(final String value, final Class<T> type) {
        return Fn.get(null,
                () -> Fn.getJvm(() -> Jackson.MAPPER.readValue(value, type)), value);
    }

    public static <T> T deserialize(final String value, final TypeReference<T> type) {
        return Fn.get(null,
                () -> Fn.getJvm(() -> Jackson.MAPPER.readValue(value, type)), value);
    }

    public static <T> List<T> convert(final List<JsonObject> result, final Class<T> clazz) {
        final List<T> entities = new ArrayList<>();
        result.forEach(item -> entities.add(Jackson.deserialize(item.encode(), clazz)));
        return entities;
    }

    private Jackson() {
    }
}
