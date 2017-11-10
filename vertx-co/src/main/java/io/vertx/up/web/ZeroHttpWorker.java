package io.vertx.up.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.annotations.Worker;

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
        bus.consumer("ZERO://USER", res -> {
            System.out.println("Hello");
            System.out.println(res.body());
            res.reply("Hello World");
        });
    }
}
