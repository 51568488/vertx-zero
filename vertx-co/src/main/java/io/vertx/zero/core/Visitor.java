package io.vertx.zero.core;

import io.vertx.exception.ZeroException;

public interface Visitor<T> {
    /**
     * @return
     * @throws ZeroException
     */
    T visit(String... keys) throws ZeroException;
}
