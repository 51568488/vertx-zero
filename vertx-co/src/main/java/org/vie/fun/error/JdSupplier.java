package org.vie.fun.error;

import org.vie.exception.ZeroException;

@FunctionalInterface
public interface JdSupplier<T> {

    T get() throws ZeroException;
}
