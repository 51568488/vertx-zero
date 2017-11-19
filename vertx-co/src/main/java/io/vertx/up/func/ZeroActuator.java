package io.vertx.up.func;

import io.vertx.zero.exception.ZeroException;

@FunctionalInterface
public interface ZeroActuator {

    void execute() throws ZeroException;
}
