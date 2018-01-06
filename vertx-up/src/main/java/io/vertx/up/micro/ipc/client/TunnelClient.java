package io.vertx.up.micro.ipc.client;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.servicediscovery.Record;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.atom.Envelop;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.flux.IpcData;
import io.vertx.up.eon.em.IpcType;
import io.vertx.up.exception._501RpcAddressWrongException;
import io.vertx.up.exception._501RpcImplementException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.discovery.IpcOrigin;
import io.vertx.up.micro.discovery.Origin;
import io.vertx.up.micro.ipc.DataEncap;
import io.vertx.up.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Rpc client, scanned etcd to get configuration.
 */
public class TunnelClient {

    private transient Vertx vertx;
    private transient Event event;
    private final transient Annal logger;

    private static final Origin ORIGIN = Instance.singleton(IpcOrigin.class);

    private static final ConcurrentMap<IpcType, RpcStub> STUBS =
            new ConcurrentHashMap<IpcType, RpcStub>() {
                {
                    put(IpcType.UNITY, Instance.singleton(UnityStub.class));
                    put(IpcType.CONSUME, Instance.singleton(ConsumeStub.class));
                    put(IpcType.DUPLIEX, Instance.singleton(DupliexStub.class));
                    put(IpcType.PRODUCE, Instance.singleton(ProduceStub.class));
                }
            };

    public static TunnelClient create(final Class<?> clazz) {
        return new TunnelClient(clazz);
    }

    private TunnelClient(final Class<?> clazz) {
        this.logger = Annal.get(clazz);
    }

    public TunnelClient connect(final Vertx vertx) {
        this.vertx = vertx;
        return this;
    }

    public TunnelClient connect(final Event event) {
        this.event = event;
        return this;
    }

    public Future<Envelop> send(final Envelop envelop) {
        // 1. Extract address
        final String address = getValue("to");
        final IpcType type = getValue("type");
        // 2. Record extract
        final Record record = findTarget();
        // 3. Convert IpcData
        final IpcData data = new IpcData();
        data.setType(type);
        data.setAddress(address);
        // 4. In data
        DataEncap.in(data, record);
        DataEncap.in(data, envelop);
        // 5. Stub
        final RpcStub stub = STUBS.getOrDefault(type, Instance.singleton(UnityStub.class));
        return stub.send(this.vertx, data);
    }

    private <T> T getValue(final String attr) {
        final Method method = this.event.getAction();
        final Annotation annotation = method.getAnnotation(Ipc.class);
        return Instance.invoke(annotation, attr);
    }

    /**
     * Here's the logical of current IPC
     * 1. The address contains all the etcd address that published
     *
     * @return Found record for IPC
     */
    private Record findTarget() {
        final ConcurrentMap<String, Record> address = ORIGIN.getRegistryData();
        final String target = getValue("to");
        final String name = getValue("name");
        // 1. Find service names
        final Record record = address.values().stream()
                .filter(item -> name.equals(item.getName()))
                .findFirst().orElse(null);
        // Service Name
        Fn.flingWeb(null == record, this.logger,
                _501RpcImplementException.class, getClass(),
                name, target, this.event.getAction());
        // Address Wrong
        Fn.flingWeb(null == record.getMetadata() ||
                        !target.equals(record.getMetadata().getString("path")), this.logger,
                _501RpcAddressWrongException.class, getClass(),
                target, name);
        this.logger.info(Info.RECORD_FOUND, record.toJson());
        return record;
    }
}
