package io.vertx.tp.qiy;

import io.vertx.core.MultiMap;

import java.io.Serializable;

/**
 * Qiy video third part model
 */
public class QiyOptions implements Serializable {

    private transient String endpoint;

    private transient String appKey;

    private transient MultiMap params;
}
