package io.vertx.up.rs.hunt;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.ce.Event;
import io.vertx.up.rs.Aim;
import org.vie.fun.HNull;

import java.lang.reflect.Method;

/**
 * OneWayAim: Event Bus: One-Way
 */
public class OneWayAim implements Aim {
    @Override
    public Handler<RoutingContext> attack(final Event event) {
        return HNull.get(() -> {
            // 1. Action extract
            final Method method = event.getAction();
            return (context) -> {

            };
        }, event);
    }
}
