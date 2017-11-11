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

    /**
     * Empty content success
     *
     * @return
     */
    public static Envelop ok() {
        return success(null);
    }

    /**
     * Success response with data
     *
     * @param data
     * @return
     */
    public static Envelop success(final JsonObject data) {
        return new Envelop(data);
    }

    /**
     * Failure response with exception
     *
     * @param error
     * @return
     */
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
        data.put(Key.BRIEF, status.message());
        data.put(Key.STATUS, status.code());
        if (null != input) {
            data.put(Key.DATA, input);
        }
        return data;
    }

    private JsonObject build(final WebException error) {
        final JsonObject data = new JsonObject();
        data.put(Key.BRIEF, error.getStatus().message());
        data.put(Key.STATUS, error.getStatus().code());
        data.put(Key.CODE, error.getCode());
        data.put(Key.MESSAGE, error.getMessage());
        return data;
    }

    /**
     * Whether this envelop is valid.
     *
     * @return
     */
    public boolean valid() {
        return null == this.error;
    }

    /**
     * Extract data part
     *
     * @return
     */
    public JsonObject data() {
        return this.data;
    }

    /**
     * Convert to response
     *
     * @return
     */
    public String response() {
        return this.data().encode();
    }

    /**
     * Extract status
     *
     * @return
     */
    public HttpStatusCode status() {
        return this.status;
    }

    /**
     * Simple set/get for User/Headers
     *
     * @return
     */
    public User user() {
        return this.user;
    }

    public MultiMap headers() {
        return this.headers;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setHeaders(final MultiMap headers) {
        this.headers = headers;
    }
}
