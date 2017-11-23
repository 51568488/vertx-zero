package io.vertx.zero.marshal;

/**
 * Read options and set default values
 *
 * @param <T>
 */
public interface Node<T> {

    T read();
}
