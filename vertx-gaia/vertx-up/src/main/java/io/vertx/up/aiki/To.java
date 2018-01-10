package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.exception._500InternalServerException;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.Jackson;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.atom.Mirror;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

class To {

    static <T> JsonObject toJson(
            final T entity,
            final String pojo) {
        return Fn.get(new JsonObject(),
                () -> Fn.getSemi(StringUtil.isNil(pojo), null,
                        () -> Jackson.serializeJson(entity),
                        () -> Mirror.create(To.class)
                                .pickup(pojo)
                                .connect(Jackson.serializeJson(entity))
                                .to().json()),
                entity);
    }

    static <T> JsonObject toJson(
            final T entity,
            final Function<JsonObject, JsonObject> convert
    ) {
        return Fn.getSemi(null == convert, null,
                () -> toJson(entity, ""),
                () -> convert.apply(toJson(entity, "")));
    }

    static <T> JsonArray toArray(
            final List<T> list,
            final Function<JsonObject, JsonObject> convert
    ) {
        final JsonArray array = new JsonArray();
        Observable.fromIterable(list)
                .filter(Objects::nonNull)
                .map(item -> toJson(item, convert))
                .subscribe(array::add);
        return array;
    }

    static <T> JsonArray toArray(
            final List<T> list,
            final String pojo
    ) {
        final JsonArray array = new JsonArray();
        Observable.fromIterable(list)
                .filter(Objects::nonNull)
                .map(item -> toJson(item, pojo))
                .subscribe(array::add);
        return array;
    }

    @SuppressWarnings("all")
    static <T> Envelop toEnvelop(
            final T entity
    ) {
        return Fn.get(Envelop.ok(),
                () -> Fn.getSemi(entity instanceof WebException, null,
                        () -> Envelop.failure((WebException) entity),
                        () -> Envelop.success(entity)),
                entity);
    }

    static WebException toError(
            final Class<? extends WebException> clazz,
            final Object... args
    ) {
        return Fn.get(new _500InternalServerException(To.class, "clazz arg is null"),
                () -> Instance.instance(clazz, args), clazz);
    }

    static Envelop toEnvelop(
            final Class<? extends WebException> clazz,
            final Object... args
    ) {
        return Envelop.failure(toError(clazz, args));
    }

    static <T> JsonObject toUnique(
            final JsonArray array,
            final String pojo
    ) {
        return Fn.getSemi(null == array || array.isEmpty(), null,
                () -> toJson(null, pojo),
                () -> toJson(array.getValue(0), pojo));
    }
}
