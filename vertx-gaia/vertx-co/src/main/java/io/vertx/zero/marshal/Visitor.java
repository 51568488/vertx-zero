package io.vertx.zero.marshal;

import io.vertx.zero.exception.ZeroException;

public interface Visitor<T> {
    /**
     * @return
     * @throws ZeroException
     */
    T visit(String... keys) throws ZeroException;
}
