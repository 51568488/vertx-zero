package io.vertx.up.exception;

import io.vertx.up.tool.StringUtil;
import io.vertx.zero.exception.UpException;

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
