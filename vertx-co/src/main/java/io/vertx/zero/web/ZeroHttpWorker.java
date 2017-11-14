package io.vertx.zero.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.exception.up.AsyncSignatureException;
import io.vertx.exception.up.WorkerArgumentException;
import io.vertx.up.annotations.Worker;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Receipt;
import org.vie.cv.Values;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.util.Instance;
import org.vie.util.log.Annal;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Default Http Server worker for different handler.
 * Recommend: Do not modify any workers that vertx zero provided.
 */
@Worker
public class ZeroHttpWorker extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroHttpWorker.class);

    private static final Set<Receipt> RECEIPTS = ZeroAnno.getReceipts();

    @Override
    public void start() {
        // 1. Get event bus
        final EventBus bus = this.vertx.eventBus();
        // 2. Consume address
        for (final Receipt receipt : RECEIPTS) {
            // 3. Deploy for each type
            final String address = receipt.getAddress();
            // 4. Get target reference and method
            final Object reference = receipt.getProxy();
            final Method method = receipt.getMethod();
            // 5. Verify
            verify(method);
            try {
                HNull.exec(() -> {
                    bus.<Envelop>consumer(address, message -> {
                        if (isVoid(method)) {
                            // void Message<Envelop>
                            Instance.invoke(reference, method.getName(), message);
                        } else {
                            // Envelop ( Envelop )
                            syncReply(message, reference, method.getName());
                        }

                    });
                }, address, reference, method);
            } catch (final Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    private void syncReply(final Message<Envelop> message,
                           final Object reference,
                           final String name) {
        // Call the handler
        final Envelop envelop = message.body();
        // Invoke
        final Envelop reply =
                Instance.invoke(reference, name, envelop);
        // Reply Message
        message.reply(reply);
    }

    private boolean isVoid(final Method method) {
        final Class<?> returnType = method.getReturnType();
        return void.class == returnType || Void.class == returnType;
    }

    private void verify(final Method method) {
        // 1. Ensure method length.
        final Class<?>[] params = method.getParameterTypes();
        final Class<?> returnType = method.getReturnType();
        // 2. The parameters
        HBool.execUp(Values.ONE != params.length,
                LOGGER, WorkerArgumentException.class,
                getClass(), method);
        // 3. Split worker flow to verify
        final Class<?> paramCls = params[Values.IDX];
        HBool.exec(isVoid(method), LOGGER,
                () -> {
                    // void method(Message<Envelop>);
                    verify(Message.class != paramCls, returnType, paramCls);
                },
                () -> {
                    // Envelop method(Envelop);
                    verify(Envelop.class != paramCls || Envelop.class != returnType,
                            returnType, paramCls);
                });
    }

    private void verify(final boolean condition,
                        final Class<?> returnType,
                        final Class<?> paramType) {
        HBool.execUp(condition,
                LOGGER, AsyncSignatureException.class,
                getClass(), returnType.getName(), paramType.getName());
    }
}
