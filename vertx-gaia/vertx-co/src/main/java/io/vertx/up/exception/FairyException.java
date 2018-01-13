package io.vertx.up.exception;

import io.vertx.zero.eon.Strings;

public abstract class FairyException extends WebException {

    private transient int dataCode;

    public FairyException(final String message) {
        super(message);
    }

    public FairyException(final Class<?> clazz, final Object... args) {
        super(Strings.EMPTY);
    }

    public void setDataCode(final int dataCode) {
        this.dataCode = dataCode;
    }

    public int getDataCode() {
        return this.dataCode;
    }
}
