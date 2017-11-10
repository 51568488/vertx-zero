package org.vie.fun.error;

import org.vie.exception.ZeroException;

@FunctionalInterface
public interface JdConsumer {

    void exec() throws ZeroException;
}
