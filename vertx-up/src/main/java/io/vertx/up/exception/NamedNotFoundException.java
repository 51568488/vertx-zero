package io.vertx.up.exception;

import io.vertx.up.tool.StringUtil;
import io.vertx.zero.exception.UpException;

import java.util.Set;

public class NamedNotFoundException extends UpException {

    public NamedNotFoundException(final Class<?> clazz,
                                  final Set<String> sets,
                                  final String value) {
        super(clazz, StringUtil.join(sets), value);
    }

    @Override
    public int getCode() {
        return -40025;
    }
}
