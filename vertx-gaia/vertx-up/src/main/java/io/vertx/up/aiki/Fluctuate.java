package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.exception.WebException;
import io.vertx.up.tool.Statute;
import io.vertx.up.tool.mirror.Instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
class Fluctuate {
    /**
     * Source -> List<F> -> List<Future<S>>
     */
    static <F, S, T> Future<List<T>> thenParallel(
            final Future<List<F>> source,
            final Function<F, Future<S>> generateFun,
            final BiFunction<F, S, T> mergeFun
    ) {
        return source.compose(first -> {
            final List<Future> secondFutures = new ArrayList<>();
            Observable.fromIterable(first)
                    .map(generateFun::apply)
                    .filter(Objects::nonNull)
                    .subscribe(secondFutures::add);
            final Future<List<T>> result = Future.future();
            CompositeFuture.all(secondFutures).setHandler(res -> {
                final List<S> secondary = res.result().list();
                // Zipper Operation, the base list is first.
                final List<T> completed = Statute.zipper(first, secondary, mergeFun);
                result.complete(completed);
            });
            return result;
        });
    }

    static Future<JsonArray> thenScatterJson(
            final Future<JsonArray> source,
            final Function<JsonObject, Future<JsonArray>> generateFun,
            final BiFunction<JsonObject, JsonArray, JsonObject> mergeFun
    ) {
        return source.compose(first -> {
            final List secondFutures = new ArrayList<>();
            Observable.fromIterable(first)
                    .map(item -> (JsonObject) item)
                    .map(generateFun::apply)
                    .subscribe(secondFutures::add);
            final Future<JsonArray> result = Future.future();
            CompositeFuture.all(secondFutures).setHandler(res -> {
                final List<JsonArray> secondary = res.result().list();
                // Zipper Operation, the base list is first
                final List<JsonObject> completed = Statute.zipper(first.getList(), secondary, mergeFun);
                result.complete(new JsonArray(completed));
            });
            return result;
        });
    }

    static Future<JsonArray> thenParallelJson(
            final Future<JsonArray> source,
            final Function<JsonObject, Future<JsonObject>> generateFun,
            final BinaryOperator<JsonObject> operatorFun
    ) {
        return source.compose(first -> {
            final List secondFutures = new ArrayList<>();
            Observable.fromIterable(first)
                    .map(item -> (JsonObject) item)
                    .map(generateFun::apply)
                    .subscribe(secondFutures::add);
            final Future<JsonArray> result = Future.future();
            CompositeFuture.all(secondFutures).setHandler(res -> {
                final List<JsonObject> secondary = res.result().list();
                // Zipper Operation, the base list is first
                final List<JsonObject> completed = Statute.zipper(first.getList(), secondary, operatorFun);
                result.complete(new JsonArray(completed));
            });
            return result;
        });
    }

    /**
     * Source ->
     * Target1
     * Target2
     */
    static <F, S, T> Future<T> thenComposite(
            final Future<F> source,
            final BiFunction<F, List<S>, T> mergeFun,
            final Supplier<Future<S>>... suppliers) {
        return source.compose(first -> {
            final List<Future> secondFutures = new ArrayList<>();
            Observable.fromArray(suppliers)
                    .map(Supplier::get)
                    .subscribe(secondFutures::add);
            final Future<T> result = Future.future();
            CompositeFuture.all(secondFutures).setHandler(res -> {
                final List<S> secondary = res.result().list();
                final T completed = mergeFun.apply(first, secondary);
                result.complete(completed);
            });
            return result;
        });
    }

    static <F, S, T> Future<T> thenComposite(
            final Future<F> source,
            final BiFunction<F, List<S>, T> mergeFun,
            final Function<F, Future<S>>... functions) {
        return source.compose(first -> {
            final List<Future> secondFutures = new ArrayList<>();
            Observable.fromArray(functions)
                    .map(item -> item.apply(first))
                    .subscribe(secondFutures::add);
            final Future<T> result = Future.future();
            CompositeFuture.all(secondFutures).setHandler(res -> {
                final List<S> secondary = res.result().list();
                final T completed = mergeFun.apply(first, secondary);
                result.complete(completed);
            });
            return result;
        });
    }

    static <T, F, R> Future<R> thenOtherwise(
            final Future<Boolean> conditionFuture,
            final Supplier<Future<T>> supplierTrue, final Function<T, R> trueFun,
            final Supplier<Future<F>> supplierFalse, final Function<F, R> falseFun
    ) {
        final Future<R> future = Future.future();
        conditionFuture.setHandler(handler -> {
            if (handler.succeeded() && handler.result()) {
                // Success & Boolean
                final Future<T> trueFuture = supplierTrue.get();
                trueFuture.setHandler(trueRes -> future.complete(trueFun.apply(trueRes.result())));
            } else {
                // Failed & Boolean = false;
                final Future<F> falseFuture = supplierFalse.get();
                falseFuture.setHandler(falseRes -> future.complete(falseFun.apply(falseRes.result())));
            }
        });
        return future;
    }

    static <T, R> Future<R> thenOtherwise(
            final Future<Boolean> conditionFuture,
            final Supplier<Future<T>> supplierTrue, final Function<T, R> trueFun,
            final Class<? extends WebException> clazz, final Object... args
    ) {
        final Future<R> future = Future.future();
        conditionFuture.setHandler(handler -> {
            if (handler.succeeded() && handler.result()) {
                // Success & Boolean
                final Future<T> trueFuture = supplierTrue.get();
                trueFuture.setHandler(trueRes -> future.complete(trueFun.apply(trueRes.result())));
            } else {
                // Failed & Boolean = false;
                if (null == clazz) {
                    future.complete();
                } else {
                    // Error existing
                    final WebException error = Instance.instance(clazz, args);
                    future.fail(error);
                }
            }
        });
        return future;
    }

    static <T> Future<T> thenError(
            final Class<? extends WebException> clazz,
            final Object... args
    ) {
        final WebException error = To.toError(clazz, args);
        return Future.failedFuture(error);
    }
}
