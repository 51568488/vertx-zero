package io.vertx.zero.exception;

import io.vertx.up.tool.StringUtil;

import java.util.Set;

public class DuplicatedWallException extends UpException {

    public DuplicatedWallException(final Class<?> classes,
                                   final Set<String> classNames) {
        super(classes, StringUtil.join(classNames));
    }

    @Override
    public int getCode() {
        return -40038;
    }
}
