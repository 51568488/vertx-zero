package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.exception.ZeroRunException;
import io.vertx.zero.log.Errors;

/**
 *
 */
public abstract class WebException extends ZeroRunException {

    private final String message;

    protected HttpStatusCode status;

    private String readible;

    public WebException(final String message) {
        super(message);
        this.message = message;
        this.status = HttpStatusCode.BAD_REQUEST;
    }

    public WebException(final Class<?> clazz, final Object... args) {
        super(Strings.EMPTY);
        this.message = Errors.normalize(clazz, getCode(), args);
        this.status = HttpStatusCode.BAD_REQUEST;
    }

    public abstract int getCode();

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpStatusCode getStatus() {
        // Default exception for 400
        return this.status;
    }

    public void setReadible(final String readible) {
        this.readible = readible;
    }

    public void setStatus(final HttpStatusCode status) {
        this.status = status;
    }

    public String getReadible() {
        return this.readible;
    }
}
