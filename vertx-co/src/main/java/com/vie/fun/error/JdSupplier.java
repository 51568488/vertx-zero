package com.vie.fun.error;

import com.vie.exception.ZeroException;

@FunctionalInterface
public interface JdSupplier<T> {

    T get() throws ZeroException;
}
