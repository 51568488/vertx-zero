package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Fn;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;

class Async {

    static <T> Future<Envelop> toFuture(
            final String pojo,
            final CompletableFuture<T> completableFuture
    ) {
        final Future<Envelop> future = Future.future();
        final Future<JsonObject> jsonFuture = toJsonFuture(pojo, completableFuture);
        future.complete(To.toEnvelop(jsonFuture.result()));
        return future;
    }

    static <T> Future<JsonObject> toJsonFuture(
            final String pojo,
            final CompletableFuture<T> completableFuture
    ) {
        final Future<JsonObject> future = Future.future();
        Fn.safeSemi(null == completableFuture, null,
                () -> future.complete(new JsonObject()),
                () -> completableFuture.thenAcceptAsync((item) -> Fn.safeSemi(
                        null == item, null,
                        () -> future.complete(new JsonObject()),
                        () -> future.complete(To.toJson(item, pojo))
                )));
        return future;
    }

    @SuppressWarnings("unchecked")
    static <T> Future<Envelop> toFuture(
            final WebException error,
            final BinaryOperator<T> operator,
            final Future<T>... futures
    ) {
        final Future<Envelop> future = Future.future();
        final BiFunction<T, T, T> biOp = null == operator ? null : operator::apply;
        CompositeFuture.all(Arrays.asList(futures)).setHandler(res -> Fn.safeSemi(res.succeeded(), null,
                () -> future.complete(
                        Fn.getSemi(null == operator || null == biOp, null,
                                () -> To.toEnvelop(res.result().list()),
                                () -> To.toEnvelop(Observable.fromIterable(res.result().list())
                                        .filter(Objects::nonNull)
                                        .map(item -> (T) item)
                                        .reduce(biOp).blockingGet())
                        )
                ),
                () -> Fn.safeSemi(null == error, null,
                        () -> future.complete(Envelop.ok()),
                        () -> future.complete(Envelop.failure(error)))
        ));
        return future;
    }
}
