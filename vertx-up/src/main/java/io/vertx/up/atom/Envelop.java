package io.vertx.up.atom;

import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.up.exception.WebException;
import io.vertx.up.tool.Jackson;
import io.vertx.up.web.ZeroSerializer;

import java.io.Serializable;

public class Envelop implements Serializable {

    private HttpStatusCode status = HttpStatusCode.OK;

    private MultiMap headers;

    private final WebException error;

    private final JsonObject data;

    private User user;

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
        return (null != this.data && this.data.containsKey(Key.DATA)) ?
                this.data.getJsonObject(Key.DATA) : this.data;
    }

    /**
     * Extract data part to t
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T data(final Class<T> clazz) {
        T reference = null;
        if (this.data.containsKey(Key.DATA)) {
            final Object content = this.data.getValue(Key.DATA);
            if (null != content) {
                reference = Jackson.deserialize(content.toString(), clazz);
            }
        }
        return reference;
    }

    /**
     * Convert to response
     *
     * @return
     */
    public String response() {
        final JsonObject response;
        if (null == this.error) {
            response = this.data;
        } else {
            response = fail(this.error);
        }
        return response.encode();
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

    private <T> Envelop(final T data) {
        this.data = build(ZeroSerializer.toSupport(data));
        this.error = null;
        this.status = HttpStatusCode.OK;
    }


    private <T> JsonObject build(final T input) {
        final JsonObject data = new JsonObject();
        final HttpStatusCode status = (null == this.error)
                ? HttpStatusCode.OK : this.error.getStatus();
        data.put(Key.DATA, input);
        return data;
    }

    // ------------------ Failure resource model ------------------


    /**
     * Empty content success
     *
     * @return
     */
    public static Envelop ok() {
        return success(null);
    }

    public static <T> Envelop success(final T entity) {
        return new Envelop(entity);
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

    /**
     * Extract message part to t
     *
     * @param message
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T data(final Message<Envelop> message,
                             final Class<T> clazz) {
        final Envelop body = message.body();
        if (null != body) {
            return body.data(clazz);
        } else {
            return null;
        }
    }

    private Envelop(final WebException error) {
        this.status = error.getStatus();
        this.error = error;
        this.data = this.fail(error);
    }

    private JsonObject fail(final WebException error) {
        final JsonObject data = new JsonObject();
        data.put(Key.CODE, error.getCode());
        data.put(Key.MESSAGE, error.getMessage());
        if (null != error.getReadible()) {
            data.put(Key.INFO, error.getReadible());
        }
        return data;
    }
}
