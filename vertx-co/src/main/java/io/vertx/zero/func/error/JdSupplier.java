package io.vertx.zero.func.error;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface JdSupplier<T> {

    T get() throws ZeroException;
}
