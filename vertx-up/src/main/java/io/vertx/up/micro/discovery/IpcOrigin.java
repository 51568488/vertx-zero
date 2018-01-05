package io.vertx.up.micro.discovery;

import io.vertx.servicediscovery.Record;
import io.vertx.up.eon.em.EtcdPath;

import java.util.concurrent.ConcurrentMap;

public class IpcOrigin extends EndPointOrigin {

    @Override
    public ConcurrentMap<String, Record> getRegistryData() {
        return readData(EtcdPath.IPC);
    }
}
