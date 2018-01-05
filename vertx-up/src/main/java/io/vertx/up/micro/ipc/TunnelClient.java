package io.vertx.up.micro.ipc;

import io.vertx.core.Vertx;
import io.vertx.servicediscovery.Record;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.discovery.IpcOrigin;
import io.vertx.up.micro.discovery.Origin;
import io.vertx.up.tool.mirror.Instance;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Rpc client, scanned etcd to get configuration.
 */
public class TunnelClient {

    private transient Vertx vertxRef;
    private transient Event event;
    private final transient Class<?> clazz;
    private final transient Annal logger;

    private static final Origin ORIGIN = Instance.singleton(IpcOrigin.class);
    private static final ConcurrentMap<String, Record> DATA = new ConcurrentHashMap<>();

    static {
        DATA.putAll(ORIGIN.getRegistryData());
    }

    public static TunnelClient create(final Class<?> clazz) {
        return new TunnelClient(clazz);
    }

    private TunnelClient(final Class<?> clazz) {
        this.clazz = clazz;
        this.logger = Annal.get(clazz);
    }

    public TunnelClient connect(final Vertx vertx) {
        this.vertxRef = vertx;
        return this;
    }

    public TunnelClient connect(final Event event) {
        this.event = event;
        return this;
    }

    public void send(final Envelop envelop) {
        System.out.println(DATA);
    }
}
