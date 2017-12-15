package io.vertx.up.tool;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.kidd.unseen.Apeak;
import io.vertx.up.tool.mirror.Types;

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
                .filter(item -> Objects.nonNull(item.getKey()))
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
                Observable.fromIterable(data)
                        .filter(Objects::nonNull)
                        .map(Map.Entry::getValue)
                        .filter(Objects::nonNull)
                        .filter(each -> JsonObject.class == each.getClass()
                                || JsonArray.class == each.getClass())
                        .map(each -> {
                            if (JsonObject.class == each.getClass()) {
                                each = to((JsonObject) each, apeak);
                            } else {
                                each = to((JsonArray) each, apeak);
                            }
                            return each;
                        }).subscribe();
            }
        }, item, item.getKey(), item.getValue());
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
