package io.vertx.zero.core;

import com.vie.exception.ZeroException;

public interface Visitor<T> {
    /**
     * @return
     * @throws ZeroException
     */
    T visit(String... keys) throws ZeroException;
}
