package io.vertx.up.micro.spider;

import io.vertx.servicediscovery.Record;

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
    Set<Record> getRegistryData();

    String HOST = "host";
    String NAME = "name";
    String PORT = "port";
    String ID = "id";
}
