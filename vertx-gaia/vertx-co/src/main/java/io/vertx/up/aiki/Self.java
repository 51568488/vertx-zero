package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.StringUtil;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
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

    static JsonObject convert(
            final JsonObject entity,
            final ConcurrentMap<String, String> mapping,
            final boolean immutable
    ) {
        final JsonObject result = immutable ? entity.copy() : entity;
        for (final String from : mapping.keySet()) {
            if (result.containsKey(from)) {
                final String to = mapping.get(from);
                result.put(to, result.getValue(from));
                // Remove before key
                result.remove(from);
            }
        }
        return result;
    }
}
