package org.vie.fun.error;

import org.vie.exception.ZeroException;

@FunctionalInterface
public interface JdBiConsumer<T, R> {

    void accept(T input, R second) throws ZeroException;
}
