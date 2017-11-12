package org.vie.fun.error;

import io.vertx.exception.ZeroException;

@FunctionalInterface
public interface JdBiConsumer<T, R> {

    void accept(T input, R second) throws ZeroException;
}
