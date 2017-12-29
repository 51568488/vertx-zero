package io.vertx.up.micro;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.annotations.Worker;
import io.vertx.up.eon.ID;
import io.vertx.up.eon.em.MessageModel;
import io.vertx.up.micro.center.ZeroRegistry;

/**
 * Get data from event bus and push metadata to Etcd.
 */
@Worker(value = MessageModel.REQUEST_MICRO_WORKER, instances = 1)
public class ZeroHttpRegistry extends AbstractVerticle {

    private transient final ZeroRegistry registry
            = ZeroRegistry.create(getClass());

    @Override
    public void start() {
        final EventBus bus = this.vertx.eventBus();
        bus.consumer(ID.Addr.REGISTRY_START, result -> {
            System.out.println(result.body());
        });
    }
}
