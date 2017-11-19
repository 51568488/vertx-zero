package io.vertx.up.func;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroSupplier<T> {

    T get() throws ZeroException;
}
