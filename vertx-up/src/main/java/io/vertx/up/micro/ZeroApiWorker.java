package io.vertx.up.micro;

import io.reactivex.Observable;
import io.vertx.core.AbstractVerticle;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.up.annotations.Worker;
import io.vertx.up.eon.em.MessageModel;
import io.vertx.up.micro.spider.EndPointOrgin;
import io.vertx.up.micro.spider.Orgin;
import io.vertx.up.tool.mirror.Instance;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Backend for service discovery
 */
@Worker(value = MessageModel.DISCOVERY_PUBLISH, instances = 1)
public class ZeroApiWorker extends AbstractVerticle {

    private static final Orgin ORIGIN = Instance.singleton(EndPointOrgin.class);

    private static final ConcurrentMap<String, Record> REGISTRITIONS
            = new ConcurrentHashMap<>();

    private static final AtomicBoolean initialized =
            new AtomicBoolean(false);

    @Override
    public void start() {
        final ServiceDiscovery discovery = ServiceDiscovery.create(this.vertx);
        // AtomicBoolean checking
        if (!initialized.get()) {

        }
        // Scan the services every 5s
        this.vertx.setPeriodic(5000, id -> {
            // Read the services
            final Set<Record> services = ORIGIN.getRegistryData();
            // Read the down services
            // Calculate
            Observable.fromIterable(services)
                    .subscribe(item -> REGISTRITIONS.put(item.getRegistration(), item));
            // Publish the services

            for (final Record service : services) {
                System.out.println(service.getRegistration());
            }
        });
    }

    private void initializeServices(final ServiceDiscovery discovery) {
        // Read the services
        
    }
}
