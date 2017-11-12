package io.vertx.exception.zero;

import io.vertx.exception.ZeroRunException;

import java.text.MessageFormat;

public class JsonFormatException extends ZeroRunException {
    public JsonFormatException(final String filename) {
        super(MessageFormat.format(Info.JSON_MSG, filename, null));
    }

    public JsonFormatException(final String filename, final Throwable ex) {
        super(MessageFormat.format(Info.JSON_MSG, filename, ex.getCause()));
    }
}
