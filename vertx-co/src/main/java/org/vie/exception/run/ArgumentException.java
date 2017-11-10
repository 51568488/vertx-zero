package org.vie.exception.run;

import org.vie.exception.ZeroRunException;

import java.text.MessageFormat;

public class ArgumentException extends ZeroRunException {

    public ArgumentException(
            final Class<?> clazz,
            final String method,
            final Integer length,
            final String op) {
        super(MessageFormat.format(Message.ARG_MSG, method, clazz.getName(), length, op));
    }
}
