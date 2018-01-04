package io.vertx.up.atom;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpStatusCode;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.up.exception.WebException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.web.ZeroSerializer;
import io.vertx.zero.eon.Strings;
import io.vertx.zero.exception.IndexExceedException;

import java.io.Serializable;

public class Envelop implements Serializable {

    private static final Annal LOGGER = Annal.get(Envelop.class);

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
     * Extract data port to T
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T data() {
        if (null != this.data && this.data.containsKey(Key.DATA)) {
            final Object value = this.data.getValue(Key.DATA);
            if (null != value) {
                return (T) value;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Extract request part to t
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T data(final Class<T> clazz) {
        T reference = null;
        if (this.data.containsKey(Key.DATA)) {
            reference = extract(this.data.getValue(Key.DATA), clazz);
        }
        return reference;
    }

    /**
     * Extract request part to t ( Direct Mode )
     *
     * @param argIndex
     * @param <T>
     * @return
     */
    public <T> T data(final Integer argIndex, final Class<T> clazz) {
        T reference = null;
        Fn.flingUp(0 > argIndex, LOGGER,
                IndexExceedException.class, getClass(), argIndex);
        if (this.data.containsKey(Key.DATA)) {
            final JsonObject raw = this.data.getJsonObject(Key.DATA);
            if (null != raw) {
                final String key = argIndex.toString();
                if (raw.containsKey(key)) {
                    reference = extract(raw.getValue(key), clazz);
                }
            }
        }
        return reference;
    }

    /**
     * Result
     *
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T extract(final Object value, final Class<T> clazz) {
        T reference = null;
        if (null != value) {
            final Object result = ZeroSerializer.getValue(clazz, value.toString());
            reference = Fn.get(() -> (T) result, result);
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

    /**
     * Read user's identifier
     *
     * @return
     */
    public String identifier(final String field) {
        return Fn.getJvm(Strings.EMPTY, () -> {
            final JsonObject credential = this.user.principal();
            return Fn.getSemi(null != credential && credential.containsKey(field),
                    () -> credential.getString(field),
                    () -> Strings.EMPTY);
        }, this.user);
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

    private <T> Envelop(final T data, final HttpStatusCode status) {
        this.data = build(ZeroSerializer.toSupport(data));
        this.error = null;
        this.status = status;
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
        return new Envelop(entity, HttpStatusCode.OK);
    }

    public static <T> Envelop success(final T entity, final HttpStatusCode status) {
        return new Envelop(entity, status);
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
