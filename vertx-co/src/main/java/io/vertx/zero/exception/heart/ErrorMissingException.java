package io.vertx.zero.exception.heart;

import io.vertx.zero.exception.ZeroRunException;

import java.text.MessageFormat;

public class ErrorMissingException extends ZeroRunException {

    public ErrorMissingException(final Integer code, final String clazz) {
        super(MessageFormat.format(Info.ECODE_MSG, String.valueOf(code), clazz));
    }
}
