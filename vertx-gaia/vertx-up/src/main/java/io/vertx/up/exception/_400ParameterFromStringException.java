package io.vertx.up.exception;

public class _400ParameterFromStringException extends WebException {

    public _400ParameterFromStringException(final Class<?> clazz,
                                            final Class<?> expectedType,
                                            final String literal) {
        super(clazz, expectedType, literal);
    }

    @Override
    public int getCode() {
        return -60004;
    }
}
