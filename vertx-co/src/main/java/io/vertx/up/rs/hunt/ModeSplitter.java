package io.vertx.up.rs.hunt;

import io.vertx.core.eventbus.Message;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.Values;
import io.vertx.up.exception.up.ReturnTypeException;
import io.vertx.up.rs.Aim;
import io.vertx.up.rs.Splitter;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HNull;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 1. AsyncAim: Event Bus: Request-Response
 * 2. SyncAim: Non-Event Bus: Request-Response
 * 3. OneWayAim: Event Bus: One-Way
 * 4. BlockAim: Non-Event Bus: One-Way
 */
public class ModeSplitter implements Splitter {

    private static final Annal LOGGER = Annal.get(ModeSplitter.class);

    private static final Set<Receipt> RECEIPTS = ZeroAnno.getReceipts();

    @Override
    public Aim distribute(final Event event) {
        return HNull.get(() -> {
            // 1. Scan method to check @Address
            final boolean annotated = event.getAction().isAnnotationPresent(Address.class);
            final Method method = event.getAction();
            final Class<?> returnType = method.getReturnType();
            // 2. Split request flow
            Aim aim = null;
            if (annotated) {
                // Event Bus
                if (Void.class == returnType || void.class == returnType) {
                    // Exception because this method must has return type to
                    // send message to event bus. It means that it require
                    // return types.
                    HBool.execUp(true, LOGGER, ReturnTypeException.class,
                            getClass(), method);
                } else {
                    // Scan the system to find replier method return type.
                    final Method replier = findReplier(event);
                    final Class<?> replierType = replier.getReturnType();
                    if (Void.class == replierType || void.class == replierType) {
                        if (isAsync(replier)) {
                            // Mode 5: Event Bus: ( Async ) Request-Response
                            aim = Instance.singleton(AsyncAim.class);
                        } else {
                            // Mode 3: Event Bus: One-Way
                            aim = Instance.singleton(OneWayAim.class);
                        }
                    } else {
                        // Mode 1: Event Bus: Request-Response
                        aim = Instance.singleton(AsyncAim.class);
                    }
                }
            } else {
                // Non Event Bus
                if (Void.class == returnType || void.class == returnType) {
                    // Mode 4: Non-Event Bus: One-Way
                    aim = Instance.singleton(BlockAim.class);
                } else {
                    // Mode 2: Non-Event Bus: Request-Response\
                    aim = Instance.singleton(SyncAim.class);
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
        final Optional<Receipt> found = RECEIPTS.stream()
                .filter(item -> address.equals(item.getAddress()))
                .findFirst();
        Method method = null;
        if (found.isPresent()) {
            method = found.get().getMethod();
            if (null == method) {
                HBool.execUp(true, LOGGER, ReturnTypeException.class,
                        getClass(), address);
            }
        } else {
            HBool.execUp(true, LOGGER, ReturnTypeException.class,
                    getClass(), address);
        }
        return method;
    }

    private transient final AtomicBoolean isReturnType = new AtomicBoolean(false);
}
