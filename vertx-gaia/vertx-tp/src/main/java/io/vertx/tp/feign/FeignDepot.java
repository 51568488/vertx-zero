package io.vertx.tp.feign;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.codec.JsonObjectDecoder;
import feign.codec.JsonObjectEncoder;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.atom.Ruler;
import io.vertx.zero.exception.DynamicKeyMissingException;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.marshal.node.Node;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Plugin for feign client
 */
public class FeignDepot implements Serializable {

    private static final Annal LOGGER = Annal.get(FeignDepot.class);
    private static final ConcurrentMap<String, FeignDepot> CACHE = new ConcurrentHashMap<>();
    private static final Node<JsonObject> TP = Node.infix("tp");
    private static final String KEY = "feign";
    /**
     * end point for feign client
     **/
    private transient String endpoint;
    /**
     * configuration information
     */
    private transient JsonObject config;
    /**
     * request options
     */
    private transient Request.Options options;
    /**
     * retry default configuration
     */
    private transient Retryer.Default defaults;

    public static FeignDepot create(final String key) {
        return Fn.pool(CACHE, key, () -> new FeignDepot(key));
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T build(final Class<T> clazz) {
        return this.build(clazz, this.endpoint, null);
    }

    public <T> T build(final Class<T> clazz, final String endpoint) {
        return this.build(clazz, endpoint, null);
    }

    /**
     * Build client for key
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T build(final Class<T> clazz, final String endpoint, final ErrorDecoder decoder) {
        final Feign.Builder builder = Feign.builder();
        if (null != this.options) {
            builder.options(this.options);
        }
        if (null != this.defaults) {
            builder.retryer(this.defaults);
        }
        builder.encoder(new JsonObjectEncoder());
        builder.decoder(new JsonObjectDecoder());
        if (null != decoder) {
            builder.errorDecoder(decoder);
        }
        return builder.target(clazz, endpoint);
    }

    private FeignDepot(final String key) {
        final JsonObject config = TP.read();
        // Check up exception for key
        Fn.flingUp(null == config || !config.containsKey(key),
                LOGGER, DynamicKeyMissingException.class,
                this.getClass(), key, config);
        // Validation passed
        if (this.verify(config, key)) {
            // Attribute, Type are all correct
            this.init(config.getJsonObject(key));
        }
    }

    public JsonObject getConfig() {
        return this.config;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    private void init(final JsonObject raw) {
        // Options
        this.initOpts(raw);
        // Config
        this.endpoint = raw.getString("endpoint");
        if (raw.containsKey("config")) {
            this.config = raw.getJsonObject("config");
        } else {
            this.config = new JsonObject();
        }
    }

    private void initOpts(final JsonObject raw) {
        // Options
        JsonObject normalized = this.getOptions();
        if (raw.containsKey("timeout")) {
            final JsonObject options = raw.getJsonObject("timeout");
            normalized = normalized.mergeIn(options);
        }
        this.options = new Request.Options(
                normalized.getInteger("connect"),
                normalized.getInteger("read"));
        // Defaults
        normalized = this.getDefaults();
        if (raw.containsKey("retry")) {
            final JsonObject defaults = raw.getJsonObject("retry");
            normalized = normalized.mergeIn(defaults);
        }
        this.defaults = new Retryer.Default(
                normalized.getInteger("period"),
                normalized.getInteger("maxPeriod"),
                normalized.getInteger("attempts")
        );
    }

    private JsonObject getDefaults() {
        final JsonObject defaults = new JsonObject();
        defaults.put("period", 5000);
        defaults.put("maxPeriod", 5000);
        defaults.put("attempts", 3);
        return defaults;
    }

    private JsonObject getOptions() {
        final JsonObject defaults = new JsonObject();
        defaults.put("connect", 1000);
        defaults.put("read", 3500);
        return defaults;
    }

    private boolean verify(final JsonObject config, final String key) {
        final Object value = config.getValue(key);
        boolean verified = true;
        if (JsonObject.class == value.getClass()) {
            // Checking the type
            try {
                Ruler.verify(KEY, (JsonObject) value);
            } catch (final ZeroException ex) {
                LOGGER.zero(ex);
                verified = false;
            }
        } else {
            LOGGER.warn(Info.TYPE_CONFLICT, key, config.encode());
            verified = false;
        }
        return verified;
    }
}
