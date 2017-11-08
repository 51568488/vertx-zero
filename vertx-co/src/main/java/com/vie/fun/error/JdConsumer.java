package com.vie.fun.error;

import com.vie.exception.ZeroException;

@FunctionalInterface
public interface JdConsumer {

    void exec() throws ZeroException;
}
