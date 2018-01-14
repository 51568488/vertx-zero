package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.Statute;
import io.vertx.zero.eon.Values;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class Dual {

    static JsonObject append(
            final JsonObject target,
            final JsonObject source,
            final boolean immutable
    ) {
        final JsonObject result = immutable ? target.copy() : target;
        Observable.fromIterable(source.fieldNames())
                .filter(key -> !target.containsKey(key))
                .subscribe(key -> result.put(key, source.getValue(key)));
        return result;
    }

    static JsonObject append(
            final JsonObject target,
            final JsonArray sources
    ) {
        Observable.fromIterable(sources)
                .map(item -> (JsonObject) item)
                .subscribe(item -> append(target, item, false));
        return target;
    }

    static JsonArray zip(
            final JsonArray target,
            final JsonArray sources,
            final String fromKey,
            final String toKey
    ) {
        final ConcurrentMap<Integer, Object> targetMap = mapIndex(target, fromKey);
        final ConcurrentMap<Object, JsonObject> sourceMap = mapZip(sources, toKey);
        final ConcurrentMap<Integer, JsonObject> merged = Statute.reduce(targetMap, sourceMap);
        final JsonArray results = new JsonArray();
        for (int idx = 0; idx < target.size(); idx++) {
            final JsonObject item = append(target.getJsonObject(idx), merged.get(idx), true);
            results.add(item);
        }
        target.clear();
        return target.addAll(results);
    }

    private static ConcurrentMap<Object, JsonObject> mapZip(
            final JsonArray sources,
            final String field
    ) {
        final ConcurrentMap<Object, JsonObject> resultMap =
                new ConcurrentHashMap<>();
        Observable.fromIterable(sources)
                .map(item -> (JsonObject) item)
                .subscribe(item -> {
                    if (item.containsKey(field)) {
                        final Object value = item.getValue(field);
                        if (null != value) {
                            resultMap.put(value, item);
                        }
                    }
                });
        return resultMap;
    }

    private static ConcurrentMap<Integer, Object> mapIndex(
            final JsonArray sources,
            final String field
    ) {
        final ConcurrentMap<Integer, Object> resultMap =
                new ConcurrentHashMap<>();
        for (int idx = Values.IDX; idx < sources.size(); idx++) {
            final JsonObject item = sources.getJsonObject(idx);
            final Object value = item.getValue(field);
            if (null != value) {
                resultMap.put(idx, value);
            }
        }
        return resultMap;
    }
}
