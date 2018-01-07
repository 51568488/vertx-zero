package io.vertx.up.func;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroBiConsumer<T, R> {

    void accept(T input, R second) throws ZeroException;
}
