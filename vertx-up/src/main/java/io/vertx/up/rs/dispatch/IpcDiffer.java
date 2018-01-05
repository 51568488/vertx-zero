package io.vertx.up.rs.dispatch;

import io.vertx.ext.web.RoutingContext;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.hunt.IpcAim;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.ReturnTypeException;

import java.lang.reflect.Method;

class IpcDiffer implements Differ<RoutingContext> {

    private static final Annal LOGGER = Annal.get(IpcDiffer.class);

    private static Differ<RoutingContext> INSTANCE = null;

    public static Differ<RoutingContext> create() {
        if (null == INSTANCE) {
            synchronized (EventDiffer.class) {
                if (null == INSTANCE) {
                    INSTANCE = new IpcDiffer();
                }
            }
        }
        return INSTANCE;
    }

    private IpcDiffer() {
    }

    @Override
    public Aim<RoutingContext> build(final Event event) {
        final Method method = event.getAction();
        final Class<?> returnType = method.getReturnType();
        // Rpc Mode only
        Aim<RoutingContext> aim = null;
        if (Void.class == returnType || void.class == returnType) {
            // Exception because this method must has return type to
            // send message to event bus. It means that it require
            // return types.
            Fn.flingUp(true, LOGGER, ReturnTypeException.class,
                    getClass(), method);
        } else {
            // Mode 6: Ipc channel enabled
            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-ipc",
                    () -> Instance.instance(IpcAim.class));
        }
        return aim;
    }
}
