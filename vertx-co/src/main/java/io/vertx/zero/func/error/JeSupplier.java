package io.vertx.zero.func.error;

@FunctionalInterface
public interface JeSupplier<T> {

    T get() throws Exception;
}
