package io.vertx.zero.core;

import org.vie.exception.ZeroException;

public interface Visitor<T> {
    /**
     * @return
     * @throws ZeroException
     */
    T visit(String... keys) throws ZeroException;
}
