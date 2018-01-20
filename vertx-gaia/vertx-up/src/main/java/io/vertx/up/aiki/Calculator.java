package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;

class Calculator {

    static void appendJson(final JsonObject target, final JsonObject source) {
        Observable.fromIterable(source.fieldNames())
                .filter(key -> !target.containsKey(key))
                .subscribe(key -> target.put(key, source.getValue(key)));
    }
}
