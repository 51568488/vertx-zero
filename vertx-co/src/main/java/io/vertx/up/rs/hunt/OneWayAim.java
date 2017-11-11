package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Event;
import io.vertx.up.ce.codec.EnvelopCodec;
import io.vertx.up.rs.Aim;
import org.vie.fun.HNull;
import org.vie.util.Instance;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * OneWayAim: Event Bus: One-Way
 */
public class OneWayAim extends BaseAim implements Aim {

    private final transient AtomicBoolean isRegistered
            = new AtomicBoolean(false);

    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return HNull.get(() -> (context) -> {
            // 1. Build Arguments
            final Object[] arguments = buildArgs(context, event);

            // 2. Method call
            final Object returnValue = invoke(event, arguments);
            final Envelop request = Envelop.success(returnValue);

            // 3. Build event bus
            final Vertx vertx = context.vertx();
            final EventBus bus = vertx.eventBus();
            {
                // Set default codec
                // Fix issue: java.lang.IllegalStateException:
                // Already a default codec registered for class class io.vertx.up.ce.Envelop
                if (!this.isRegistered.getAndSet(true)) {
                    bus.registerDefaultCodec(Envelop.class,
                            Instance.singleton(EnvelopCodec.class));
                }
            }
            // 4. Send message
            final String address = address(event);
            bus.<Envelop>send(address, request, handler -> {
                final Envelop response;
                if (handler.succeeded()) {
                    // One Way message
                    response = Envelop.ok();
                } else {
                    response = failure(address, handler);
                }
                Answer.reply(context, response, event);
            });
        }, event);
    }
}
