package io.vertx.up.exception;

import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;
import io.vertx.up.tool.StringUtil;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.exception.ZeroRunException;
import io.vertx.zero.log.Errors;

/**
 *
 */
public abstract class WebException extends ZeroRunException {

    protected static final String MESSAGE = "message";
    protected static final String INFO = "info";
    protected static final String CODE = "code";

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
        this.message = Errors.normalizeWeb(clazz, this.getCode(), args);
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

    public JsonObject toJson() {
        final JsonObject data = new JsonObject();
        data.put(CODE, this.getCode());
        data.put(MESSAGE, this.getMessage());
        if (StringUtil.notNil(this.readible)) {
            data.put(INFO, this.readible);
        }
        return data;
    }
}
