package io.vertx.up.micro;

import io.vertx.servicediscovery.Record;
import io.vertx.up.micro.discovery.EndPointOrigin;
import io.vertx.up.micro.discovery.Origin;
import io.vertx.up.tool.mirror.Instance;

import java.util.concurrent.ConcurrentMap;

public class EndPointOrginTc {

    public void testRead() {
        final Origin orgin = Instance.singleton(EndPointOrigin.class);
        final ConcurrentMap<String, Record> records = orgin.getRegistryData();
        records.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value.toJson().encodePrettily());
        });
    }
}
