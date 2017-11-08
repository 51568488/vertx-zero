package com.vie.fun.error;

import com.vie.exception.ZeroException;

@FunctionalInterface
public interface JdBiConsumer<T, R> {

    void accept(T input, R second) throws ZeroException;
}
