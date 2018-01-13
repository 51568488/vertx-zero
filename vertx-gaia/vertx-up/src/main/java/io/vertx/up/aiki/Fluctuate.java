package io.vertx.up.aiki;

import io.reactivex.Observable;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.up.exception.WebException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
class Fluctuate {
    /**
     * Source ->
     * Target1
     * Target2
     */
    static <F, S, T> Future<T> thenComposite(
            final BiFunction<F, List<S>, T> mergeFun,
            final Future<F> source,
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
            final BiFunction<F, List<S>, T> mergeFun,
            final Future<F> source,
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
            final Future<T> trueFuture, final Function<T, R> trueFun,
            final Future<F> falseFuture, final Function<F, R> falseFun
    ) {
        final Future<R> future = Future.future();
        conditionFuture.setHandler(handler -> {
            if (handler.succeeded() && handler.result()) {
                // Success & Boolean
                trueFuture.setHandler(trueRes -> future.complete(trueFun.apply(trueRes.result())));
            } else {
                // Failed & Boolean = false;
                falseFuture.setHandler(falseRes -> future.complete(falseFun.apply(falseRes.result())));
            }
        });
        return future;
    }

    static <T, R> Future<R> thenOtherwise(
            final Future<Boolean> conditionFuture,
            final Future<T> trueFuture, final Function<T, R> trueFun,
            final WebException error
    ) {
        final Future<R> future = Future.future();
        conditionFuture.setHandler(handler -> {
            if (handler.succeeded() && handler.result()) {
                // Success & Boolean
                trueFuture.setHandler(trueRes -> future.complete(trueFun.apply(trueRes.result())));
            } else {
                // Failed & Boolean = false;
                future.fail(error);
            }
        });
        return future;
    }
}
