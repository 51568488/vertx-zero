package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.StringUtil;

import java.util.Objects;
import java.util.Set;
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
}
