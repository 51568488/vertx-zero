package io.vertx.up.tool;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.unseen.Apeak;
import io.vertx.up.tool.mirror.Types;
import io.vertx.zero.eon.Strings;

import java.util.Map;
import java.util.Objects;

/**
 * Field mapping tool to process JsonObject or JsonArray
 */
public class Vertical {
    /**
     * Default support recursion
     *
     * @param data
     * @param apeak
     * @return
     */
    public static JsonObject to(
            final JsonObject data,
            final Apeak apeak
    ) {
        return to(data, apeak, true);
    }

    /**
     * Process JsonObject
     *
     * @param data
     * @param apeak
     * @return
     */
    public static JsonObject to(
            final JsonObject data,
            final Apeak apeak,
            final boolean recursion
    ) {
        Observable.fromIterable(apeak)
                .filter(Objects::nonNull)
                .filter(item -> Objects.nonNull(item.getKey()) && Objects.nonNull(item.getValue()))
                .subscribe(item -> execute(data, apeak, item, recursion));
        return data;
    }

    private static void execute(
            final JsonObject data,
            final Apeak apeak,
            final Map.Entry<String, String> item,
            final boolean recursion) {
        Fn.safeNull(() -> {
            final Object value = data.getValue(item.getKey());
            data.put(item.getValue(), value);
            data.remove(item.getKey());
            if (recursion) {
                Observable.fromIterable(data.fieldNames())
                        .filter(Objects::nonNull)
                        .map(field -> {
                            Object reference = data.getValue(field);
                            if (null == reference) {
                                // Fix RxJava2 null pointer issue.
                                reference = Strings.EMPTY;
                            } else {
                                if (Types.isJObject(reference)) {
                                    reference = to((JsonObject) reference, apeak);
                                } else if (Types.isJArray(reference)) {
                                    reference = to((JsonArray) reference, apeak);
                                }
                            }
                            return reference;
                        }).subscribe();
            }
        }, data, item, item.getKey(), item.getValue());
    }

    public static JsonArray to(
            final JsonArray array,
            final Apeak apeak
    ) {
        return to(array, apeak, true);
    }

    /**
     * Process JsonArray
     *
     * @param array
     * @param apeak
     * @return
     */
    public static JsonArray to(
            final JsonArray array,
            final Apeak apeak,
            final boolean recursion
    ) {
        Observable.fromIterable(array)
                .filter(Objects::nonNull)
                .filter(Types::isJObject)
                .map(item -> (JsonObject) item)
                .subscribe(item -> to(item, apeak, recursion));
        return array;
    }
}
