package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;

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
}
