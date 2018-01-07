package io.vertx.zero.exception;

public class ConfigKeyMissingException extends UpException {

    public ConfigKeyMissingException(final Class<?> clazz,
                                     final String key) {
        super(clazz, key);
    }

    @Override
    public int getCode() {
        return -40020;
    }
}
