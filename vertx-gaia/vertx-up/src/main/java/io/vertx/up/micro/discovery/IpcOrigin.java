package io.vertx.up.micro.discovery;

import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.Status;
import io.vertx.up.eon.em.EtcdPath;

import java.util.concurrent.ConcurrentMap;

public class IpcOrigin extends EndPointOrigin {

    @Override
    public ConcurrentMap<String, Record> getRegistryData() {
        final ConcurrentMap<String, Record> map = readData(EtcdPath.IPC);
        for (final Record record : map.values()) {
            record.setStatus(Status.UP);
            record.setType("IPC");
            // Alpn Enabled for Rpc, ssl must be true.
            record.getLocation().put("ssl", Boolean.TRUE);
        }
        return map;
    }
}
