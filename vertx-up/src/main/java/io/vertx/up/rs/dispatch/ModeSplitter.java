package io.vertx.up.rs.dispatch;

import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.RoutingContext;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.agent.Event;
import io.vertx.up.atom.worker.Receipt;
import io.vertx.up.exception.ReturnTypeException;
import io.vertx.up.exception.WorkerMissingException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.hunt.AsyncAim;
import io.vertx.up.rs.hunt.BlockAim;
import io.vertx.up.rs.hunt.OneWayAim;
import io.vertx.up.rs.hunt.SyncAim;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.eon.Values;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Splitter to get executor reference.
 * It will happen in startup of route building to avoid
 * request resource spending.
 * 1. Level 1: Distinguish whether enable EventBus
 * EventBus mode: Async
 * Non-EventBus mode: Sync
 * 2. Level 2: Distinguish the request mode
 * One-Way mode: No response needed. ( Return Type )
 * Request-Response mode: Must require response. ( Return Type )
 * Support modes:
 * 1. AsyncAim: Event Bus: Request-Response
 * 2. SyncAim: Non-Event Bus: Request-Response
 * 3. OneWayAim: Event Bus: One-Way
 * 4. BlockAim: Non-Event Bus: One-Way
 */
public class ModeSplitter {

    private static final Annal LOGGER = Annal.get(ModeSplitter.class);

    private static final Set<Receipt> RECEIPTS = ZeroAnno.getReceipts();

    public Aim<RoutingContext> distribute(final Event event) {
        return Fn.get(() -> {
            // 1. Scan method to check @Address
            final boolean annotated = event.getAction().isAnnotationPresent(Address.class);
            final Method method = event.getAction();
            final Class<?> returnType = method.getReturnType();
            // 2. Split request flow
            Aim<RoutingContext> aim = null;
            if (annotated) {
                // Event Bus
                if (Void.class == returnType || void.class == returnType) {
                    // Exception because this method must has return type to
                    // send message to event bus. It means that it require
                    // return types.
                    Fn.flingUp(true, LOGGER, ReturnTypeException.class,
                            getClass(), method);
                } else {
                    // Scan the system to find replier method return type.
                    final Method replier = findReplier(event);
                    final Class<?> replierType = replier.getReturnType();
                    if (Void.class == replierType || void.class == replierType) {
                        if (isAsync(replier)) {
                            // Mode 5: Event Bus: ( Async ) Request-Response
                            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "5",
                                    () -> Instance.instance(AsyncAim.class));
                        } else {
                            // Mode 3: Event Bus: One-Way
                            aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "3",
                                    () -> Instance.instance(OneWayAim.class));
                        }
                    } else {
                        // Mode 1: Event Bus: Request-Response
                        aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "1",
                                () -> Instance.instance(AsyncAim.class));
                    }
                }
            } else {
                // Non Event Bus
                if (Void.class == returnType || void.class == returnType) {
                    // Mode 4: Non-Event Bus: One-Way
                    aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "4",
                            () -> Instance.instance(BlockAim.class));
                } else {
                    // Mode 2: Non-Event Bus: Request-Response\
                    aim = Fn.pool(Pool.AIMS, Thread.currentThread().getName() + "2",
                            () -> Instance.instance(SyncAim.class));
                }
            }
            return aim;
        }, event, event.getAction());
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

    /**
     * If the event but enabled, it means that zero system must find
     * the Address matched target worker Receipt, this action is trying to
     * find the correct Receipt.
     *
     * @param event
     * @return
     */
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
