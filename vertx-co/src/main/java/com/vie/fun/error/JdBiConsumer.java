package com.vie.fun.error;

import com.vie.hors.ZeroException;

@FunctionalInterface
public interface JdBiConsumer<T, R> {

    void accept(T input, R second) throws ZeroException;
}
