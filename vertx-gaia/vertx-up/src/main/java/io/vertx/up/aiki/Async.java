package io.vertx.up.aiki;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;

import java.util.List;
import java.util.concurrent.CompletableFuture;

class Async {

    static <T> Future<Envelop> toSingle(
            final String pojo,
            final CompletableFuture<T> completableFuture
    ) {
        final Future<Envelop> future = Future.future();
        final Future<JsonObject> jsonFuture = Async.toJsonFuture(pojo, completableFuture);
        // future.complete(To.toEnvelop(jsonFuture.result()));
        jsonFuture.setHandler(item -> future.complete(To.toEnvelop(item.result())));
        return future;
    }

    static <T> Future<Envelop> toUnique(
            final String pojo,
            final CompletableFuture<List<T>> completableFuture
    ) {
        final Future<Envelop> future = Future.future();
        final Future<JsonArray> arrayFuture = Async.toArrayFuture(pojo, completableFuture);
        // future.complete(To.toEnvelop(To.toUnique(arrayFuture.result(), pojo)));
        arrayFuture.setHandler(item -> future.complete(To.toEnvelop(To.toUnique(item.result(), pojo))));
        return future;
    }

    static <T> Future<Envelop> toMulti(
            final String pojo,
            final CompletableFuture<List<T>> completableFuture
    ) {
        final Future<Envelop> future = Future.future();
        final Future<JsonArray> arrayFuture = Async.toArrayFuture(pojo, completableFuture);
        // future.complete(To.toEnvelop(arrayFuture.result()));
        arrayFuture.setHandler(item -> future.complete(To.toEnvelop(item.result())));
        return future;
    }

    static <T> Future<T> toFuture(
            final CompletableFuture<T> completableFuture
    ) {
        final Future<T> future = Future.future();
        completableFuture.thenAcceptAsync(future::complete)
                .exceptionally((ex) -> {
                    future.fail(ex);
                    return null;
                });
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
                )).exceptionally((ex) -> {
                    future.fail(ex);
                    return null;
                }));
        return future;
    }

    static <T> Future<JsonArray> toArrayFuture(
            final String pojo,
            final CompletableFuture<List<T>> completableFuture
    ) {
        final Future<JsonArray> future = Future.future();
        Fn.safeSemi(null == completableFuture, null,
                () -> future.complete(new JsonArray()),
                () -> completableFuture.thenAcceptAsync((item) -> Fn.safeSemi(
                        null == item, null,
                        () -> future.complete(new JsonArray()),
                        () -> future.complete(To.toArray(item, pojo))
                )).exceptionally((ex) -> {
                    future.fail(ex);
                    return null;
                }));
        return future;
    }


}
