package io.vertx.up.aiki;


import io.vertx.core.Future;

import java.util.function.Consumer;

class Wait {

    static <T> Future<T> then(final Consumer<Future<T>> consumer) {
        final Future<T> future = Future.future();
        consumer.accept(future);
        return future;
    }
}
