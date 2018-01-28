package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.StringUtil;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Two object calculation
 */
class Self {

    static JsonObject deNull(
            final JsonObject entity,
            final boolean immutable) {
        final JsonObject result = immutable ? entity.copy() : entity;
        final Set<String> keys = entity.fieldNames()
                .stream()
                .filter(field -> Objects.isNull(entity.getValue(field)))
                .collect(Collectors.toSet());
        Observable.fromIterable(keys)
                .subscribe(result::remove);
        return result;
    }

    static JsonObject remove(
            final JsonObject entity,
            final boolean immutable,
            final String... keys
    ) {
        final JsonObject result = immutable ? entity.copy() : entity;
        Observable.fromArray(keys)
                .filter(StringUtil::notNil)
                .map(result::remove)
                .subscribe();
        return result;
    }

    static JsonObject copy(
            final JsonObject entity,
            final String from,
            final String to,
            final boolean immutable
    ) {
        final JsonObject result = immutable ? entity.copy() : entity;
        if (StringUtil.notNil(to) && entity.containsKey(from)) {
            result.put(to, entity.getValue(from));
        }
        return result;
    }

    static JsonArray copy(
            final JsonArray array,
            final String from,
            final String to,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? array.copy() : array;
        Observable.fromIterable(result)
                .map(item -> (JsonObject) item)
                .subscribe(item -> copy(item, from, to, false));
        return result;
    }

    static JsonArray filter(
            final JsonArray array,
            final Predicate<JsonObject> testFun,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? array.copy() : array;
        final JsonArray filtered = new JsonArray();
        Observable.fromIterable(result)
                .map(item -> (JsonObject) item)
                .filter(testFun::test)
                .subscribe(filtered::add);
        result.clear().addAll(filtered);
        return result;
    }

    static JsonObject defaultValue(
            final JsonObject json,
            final JsonObject filled,
            final boolean immutable
    ) {
        final JsonObject result = immutable ? json.copy() : json;
        for (final String field : filled.fieldNames()) {
            defaultValue(result, field, filled.getValue(field), false);
        }
        return result;
    }

    static JsonArray defaultValue(
            final JsonArray jsonArray,
            final JsonObject filled,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? jsonArray.copy() : jsonArray;
        Observable.fromIterable(result)
                .map(item -> (JsonObject) item)
                .subscribe(item -> defaultValue(item, filled, false));
        return result;
    }

    static JsonArray defaultValue(
            final JsonArray jsonArray,
            final String field,
            final Object value,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? jsonArray.copy() : jsonArray;
        Observable.fromIterable(result)
                .map(item -> (JsonObject) item)
                .subscribe(item -> defaultValue(item, field, value, false));
        return result;
    }

    static JsonObject defaultValue(
            final JsonObject json,
            final String field,
            final Object value,
            final boolean immutable) {
        final JsonObject result = immutable ? json.copy() : json;
        if (Objects.isNull(result.getValue(field))) {
            result.put(field, result.getValue(field));
        }
        return result;
    }

    static JsonArray vertical(
            final JsonArray array,
            final String field,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? array.copy() : array;
        final JsonArray processed = new JsonArray();
        Observable.fromIterable(result)
                .map((item) -> (JsonObject) item)
                .map(item -> item.getValue(field))
                .subscribe(processed::add);
        result.clear().addAll(processed);
        return result;
    }

    @SuppressWarnings("unchecked")
    static <I, O> JsonObject convert(
            final JsonObject entity,
            final String field,
            final Function<I, O> function,
            final boolean immutable
    ) {
        final JsonObject result = immutable ? entity.copy() : entity;
        final Object value = result.getValue(field);
        if (null != value) {
            final I input = (I) value;
            result.put(field, function.apply(input));
        }
        return result;
    }

    static <I, O> JsonArray convert(
            final JsonArray array,
            final String field,
            final Function<I, O> function,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? array.copy() : array;
        Observable.fromIterable(result)
                .map(item -> (JsonObject) item)
                .subscribe(item -> convert(item, field, function, false));
        return result;
    }

    static JsonObject convert(
            final JsonObject entity,
            final ConcurrentMap<String, String> mapping,
            final boolean immutable
    ) {
        final JsonObject result = new JsonObject();
        final Set<String> keys = new HashSet<>();
        for (final String from : entity.fieldNames()) {
            // Find to field
            // JsonArray loop
            final Object value = entity.getValue(from);
            if (null == value) {
                // null pointer
                if (mapping.containsKey(from)) {
                    final String to = mapping.get(from);
                    result.put(to, value);
                    keys.add(from);
                }
            } else {
                if (JsonArray.class == value.getClass()) {
                    // JsonArray
                    result.put(from, convert((JsonArray) value, mapping, false));
                } else if (JsonObject.class == value.getClass()) {
                    // JsonObject
                    result.put(from, convert((JsonObject) value, mapping, false));
                } else {
                    // Other Data
                    if (mapping.containsKey(from)) {
                        final String to = mapping.get(from);
                        result.put(to, value);
                        keys.add(from);
                    }
                }
            }
        }
        keys.forEach(key -> {
            // Remove all keys from different JsonObject
            entity.remove(key);
            result.remove(key);
        });
        return immutable ? result : entity.mergeIn(result, true);
    }

    static JsonArray convert(
            final JsonArray array,
            final ConcurrentMap<String, String> mapping,
            final boolean immutable
    ) {
        final JsonArray result = immutable ? array.copy() : array;
        Observable.fromIterable(result)
                .map(item -> (JsonObject) item)
                .subscribe(item -> convert(item, mapping, false));
        return result;
    }
}
