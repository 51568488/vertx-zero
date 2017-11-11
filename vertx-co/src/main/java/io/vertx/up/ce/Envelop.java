package io.vertx.up.ce;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import org.vie.exception.WebException;
import org.vie.util.Jackson;

import java.io.Serializable;

public class Envelop implements Serializable {

    private HttpStatusCode status = HttpStatusCode.OK;

    private MultiMap headers;

    private final WebException error;

    private final JsonObject data;

    private User user;

    public static <T> Envelop success(final T entity) {
        final String literal = Jackson.serialize(entity);
        return success(new JsonObject(literal));
    }

    public static Envelop success(final JsonObject data) {
        return new Envelop(data);
    }

    public static Envelop failure(final WebException error) {
        return new Envelop(error);
    }

    private Envelop(final JsonObject data) {
        this.error = null;
        this.data = build(data);
        this.status = HttpStatusCode.OK;
    }

    private Envelop(final WebException error) {
        this.status = error.getStatus();
        this.error = error;
        this.data = this.build(error);
    }

    private JsonObject build(final JsonObject input) {
        final JsonObject data = new JsonObject();
        final HttpStatusCode status = (null == this.error)
                ? HttpStatusCode.OK : this.error.getStatus();
        data.put(Response.BRIEF, status.message());
        data.put(Response.STATUS, status.code());
        if (null != input) {
            data.put(Response.DATA, input);
        }
        return data;
    }

    private JsonObject build(final WebException error) {
        final JsonObject data = new JsonObject();
        data.put(Response.BRIEF, error.getStatus().message());
        data.put(Response.STATUS, error.getStatus().code());
        data.put(Response.CODE, error.getCode());
        data.put(Response.MESSAGE, error.getMessage());
        return data;
    }

    public boolean valid() {
        return null == this.error;
    }

    public JsonObject getData() {
        return this.data;
    }

    public HttpStatusCode getStatus() {
        return this.status;
    }

    /**
     * Simple set/get for User/Headers
     *
     * @return
     */
    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public MultiMap getHeaders() {
        return this.headers;
    }

    public void setHeaders(final MultiMap headers) {
        this.headers = headers;
    }
}
