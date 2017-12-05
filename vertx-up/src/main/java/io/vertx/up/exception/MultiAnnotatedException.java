package io.vertx.up.exception;

import io.vertx.up.tool.StringUtil;
import io.vertx.zero.exception.UpException;

import java.util.Set;

public class MultiAnnotatedException extends UpException {

    public MultiAnnotatedException(final Class<?> clazz,
                                   final String className,
                                   final String name,
                                   final Set<String> set) {
        super(clazz, className, name, StringUtil.join(set));
    }

    @Override
    public int getCode() {
        return -40021;
    }
}
