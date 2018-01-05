package io.vertx.up.rs.dispatch;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.hunt.BlockAim;
import io.vertx.up.rs.hunt.SyncAim;
import io.vertx.up.tool.mirror.Instance;

import java.lang.reflect.Method;

/**
 * EventBus disabled for request
 * 1. SyncAim: Non-Event Bus: Request -> Response
 * 2. BlockAim: Non-Event Bus: Request -> (TRUE/FALSE)
 */
class CommonDiffer implements Differ<RoutingContext> {

    private static Differ<RoutingContext> INSTANCE = null;

    public static Differ<RoutingContext> create() {
        if (null == INSTANCE) {
            synchronized (EventDiffer.class) {
                if (null == INSTANCE) {
                    INSTANCE = new CommonDiffer();
                }
            }
        }
        return INSTANCE;
    }

    private CommonDiffer() {
    }

    @Override
    public Aim<RoutingContext> build(final Event event) {
        final Method method = event.getAction();
        final Class<?> returnType = method.getReturnType();
        Aim<RoutingContext> aim = null;
        if (Void.class == returnType || void.class == returnType) {
            // Mode 4: Non-Event Bus: One-Way
            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-block",
                    () -> Instance.instance(BlockAim.class));
        } else {
            // Mode 2: Non-Event Bus: Request-Response\
            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-sync",
                    () -> Instance.instance(SyncAim.class));
        }
        return aim;
    }
}
