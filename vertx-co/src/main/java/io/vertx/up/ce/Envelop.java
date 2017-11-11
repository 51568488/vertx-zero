package io.vertx.up.ce;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import org.vie.exception.WebException;
import org.vie.util.Jackson;
import org.vie.util.Types;

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
        return this.data;
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

    private <T> Envelop(final T data) {
        if (Types.isSerialized(data)) {
            this.data = build(data);
        } else {
            final String literal = Jackson.serialize(data);
            if (Types.isArray(data)) {
                this.data = build(new JsonArray(literal));
            } else {
                this.data = build(new JsonObject(literal));
            }
        }
        this.error = null;
        this.status = HttpStatusCode.OK;
    }


    private <T> JsonObject build(final T input) {
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

    // ------------------ Failure resource model ------------------

    /**
     * Failure response with exception
     *
     * @param error
     * @return
     */
    public static Envelop failure(final WebException error) {
        return new Envelop(error);
    }

    private Envelop(final WebException error) {
        this.status = error.getStatus();
        this.error = error;
        this.data = this.fail(error);
    }

    private JsonObject fail(final WebException error) {
        final JsonObject data = new JsonObject();
        data.put(Key.BRIEF, error.getStatus().message());
        data.put(Key.STATUS, error.getStatus().code());
        data.put(Key.CODE, error.getCode());
        data.put(Key.MESSAGE, error.getMessage());
        return data;
    }
}
