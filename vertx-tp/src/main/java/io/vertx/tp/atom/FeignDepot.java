package io.vertx.tp.atom;

import feign.Request;
import feign.Retryer;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;

import java.io.Serializable;

/**
 * Plugin for feign client
 */
public class FeignDepot implements Serializable {

    private static final Annal LOGGER = Annal.get(FeignDepot.class);
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
}
