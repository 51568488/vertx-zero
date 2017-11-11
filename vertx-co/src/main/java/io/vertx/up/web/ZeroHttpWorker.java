package io.vertx.up.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.annotations.Worker;
import io.vertx.up.ce.Envelop;

/**
 * Default Http Server worker for different handler.
 * Recommend: Do not modify any workers that vertx zero provided.
 */
@Worker
public class ZeroHttpWorker extends AbstractVerticle {

    @Override
    public void start() {
        // 1. Get event bus
        final EventBus bus = this.vertx.eventBus();
        // 2. Consume address
        bus.<Envelop>consumer("ZERO://USER", res -> {
            System.out.println("Hello");
            final Envelop envelop = res.body();
            System.out.println(envelop.getData());
            res.reply("Hello World");
        });
    }
}
