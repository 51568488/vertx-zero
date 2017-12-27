package io.vertx.up.web;

import io.vertx.core.json.JsonArray;
import io.vertx.tp.etcd.center.EtcdData;

/**
 * Zero registry center to write/read data with Etcd for zero micro service
 * This will be called by ZeroRpcAgent class to write service meta.
 */
public class ZeroRegistry {

    private final transient Class<?> useCls;
    private final transient EtcdData etcd;

    public static ZeroRegistry create(final Class<?> useCls) {
        return new ZeroRegistry(useCls);
    }

    private ZeroRegistry(final Class<?> useCls) {
        this.useCls = useCls;
        this.etcd = EtcdData.create(useCls);
    }

    public JsonArray getConfig() {
        return this.etcd.getConfig();
    }
}
