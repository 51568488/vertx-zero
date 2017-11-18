package io.vertx.up.exception;

import io.vertx.zero.exception.UpException;
import io.vertx.zero.tool.StringUtil;

import java.util.Set;

public class NamedImplementionException extends UpException {

    public NamedImplementionException(final Class<?> clazz,
                                      final Set<String> sets,
                                      final String interfaceStr) {
        super(clazz, StringUtil.join(sets), interfaceStr);
    }

    @Override
    public int getCode() {
        return -40024;
    }
}
