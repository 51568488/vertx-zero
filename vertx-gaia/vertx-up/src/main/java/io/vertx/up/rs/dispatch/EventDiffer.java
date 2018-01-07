package io.vertx.up.rs.dispatch;

import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.worker.Receipt;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.hunt.AsyncAim;
import io.vertx.up.rs.hunt.OneWayAim;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.ReturnTypeException;
import io.vertx.zero.exception.WorkerMissingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * EventBus enabled mode for request
 * 1. AsyncAim: Request -> Agent -> EventBus -> Worker -> Envelop Response
 * 2. OneWayAim: Request -> Agent -> EventBus -> Worker -> (TRUE/FALSE)
 * 5. Vertx AsyncAim: Request -> Agent -> EventBus -> Worker -> void Response(Replier)
 */
class EventDiffer implements Differ<RoutingContext> {

    private static final Annal LOGGER = Annal.get(EventDiffer.class);

    private static final Set<Receipt> RECEIPTS = ZeroAnno.getReceipts();

    private static Differ<RoutingContext> INSTANCE = null;

    public static Differ<RoutingContext> create() {
        if (null == INSTANCE) {
            synchronized (EventDiffer.class) {
                if (null == INSTANCE) {
                    INSTANCE = new EventDiffer();
                }
            }
        }
        return INSTANCE;
    }

    private EventDiffer() {
    }

    @Override
    public Aim<RoutingContext> build(final Event event) {
        Aim<RoutingContext> aim = null;
        final Method replier = findReplier(event);
        final Method method = event.getAction();
        final Class<?> returnType = method.getReturnType();
        if (Void.class == returnType || void.class == returnType) {
            // Exception because this method must has return type to
            // send message to event bus. It means that it require
            // return types.
            Fn.flingUp(true, LOGGER, ReturnTypeException.class,
                    getClass(), method);
        } else {
            final Class<?> replierType = replier.getReturnType();
            if (Void.class == replierType || void.class == replierType) {
                if (isAsync(replier)) {
                    // Mode 5: Event Bus: ( Async ) Request-Response
                    aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-vert.x",
                            () -> Instance.instance(AsyncAim.class));
                } else {
                    // Mode 3: Event Bus: One-Way
                    aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-oneway",
                            () -> Instance.instance(OneWayAim.class));
                }
            } else {
                // Mode 1: Event Bus: Request-Response
                aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "-mode-java",
                        () -> Instance.instance(AsyncAim.class));
            }
        }
        return aim;
    }

    private boolean isAsync(final Method method) {
        boolean async = false;
        final Class<?>[] paramTypes = method.getParameterTypes();
        if (Values.ONE == paramTypes.length) {
            final Class<?> argumentCls = paramTypes[0];
            if (Message.class == argumentCls) {
                async = true;
            }
        }
        return async;
    }

    private Method findReplier(final Event event) {
        final Annotation annotation = event.getAction().getDeclaredAnnotation(Address.class);
        final String address = Instance.invoke(annotation, "value");
        // Here address mustn't be null or empty
        final Receipt found = RECEIPTS.stream()
                .filter(item -> address.equals(item.getAddress()))
                .findFirst().orElse(null);
        final Method method;
        // Get null found throw exception.
        Fn.flingUp(null == found, LOGGER, WorkerMissingException.class,
                getClass(), address);

        method = found.getMethod();

        Fn.flingUp(null == method, LOGGER, WorkerMissingException.class,
                getClass(), address);
        return method;
    }
}
