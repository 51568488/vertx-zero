package io.vertx.zero.exception;

import io.vertx.up.tool.StringUtil;

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
