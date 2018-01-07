package io.vertx.up.rs;

public interface Extractor<T> {

    T extract(Class<?> clazz);
}
