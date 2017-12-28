package io.vertx.up.micro.spider;

import io.vertx.core.json.JsonObject;

import java.util.Set;

/**
 * Service discovery metadata discovery
 * 1. Backend : The worker will publish service out.
 * 2. Frontend : The api gateway will do discovery
 */
public interface Orgin {
    /**
     * Get backend
     *
     * @return
     */
    Set<JsonObject> getRegistryData();
}
