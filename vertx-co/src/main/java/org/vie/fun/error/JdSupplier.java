package org.vie.fun.error;

import io.vertx.exception.ZeroException;

@FunctionalInterface
public interface JdSupplier<T> {

    T get() throws ZeroException;
}
