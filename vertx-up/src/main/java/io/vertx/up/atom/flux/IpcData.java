package io.vertx.up.atom.flux;

import io.vertx.core.json.JsonObject;

import java.io.Serializable;
import java.nio.Buffer;

public class IpcData implements Serializable {
    /**
     * Call service name
     */
    private String service;
    /**
     * Call data
     */
    private Buffer buffer;
    /**
     * Additional configuration
     */
    private JsonObject additional;
    /**
     * Address
     */
    private String address;
}
