package org.vie.fun.error;

import io.vertx.exception.ZeroException;

@FunctionalInterface
public interface JdConsumer {

    void exec() throws ZeroException;
}
