package io.vertx.zero.func.error;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface JdBiConsumer<T, R> {

    void accept(T input, R second) throws ZeroException;
}
