package io.vertx.up.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.up.annotations.Worker;
import io.vertx.up.ce.Envelop;
import io.vertx.up.ce.Receipt;
import io.vertx.up.ce.codec.EnvelopCodec;
import org.vie.fun.HNull;
import org.vie.util.Instance;
import org.vie.util.log.Annal;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Default Http Server worker for different handler.
 * Recommend: Do not modify any workers that vertx zero provided.
 */
@Worker
public class ZeroHttpWorker extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroHttpWorker.class);

    private static final Set<Receipt> RECEIPTS = ZeroAnno.getReceipts();

    private final transient AtomicBoolean isRegistered
            = new AtomicBoolean(true);

    @Override
    public void start() {
        // 1. Get event bus
        final EventBus bus = this.vertx.eventBus();
        // Codec
        if (this.isRegistered.getAndSet(false)) {
            bus.registerDefaultCodec(Envelop.class,
                    Instance.singleton(EnvelopCodec.class));
        }
        // 2. Consume address
        for (final Receipt receipt : RECEIPTS) {
            // 3. Deploy for each type
            final String address = receipt.getAddress();
            // 4. Get target reference and method
            final Object reference = receipt.getProxy();
            final Method method = receipt.getMethod();
            HNull.exec(() -> {
                bus.<Envelop>consumer(address, res -> {
                    // 5. Call the handler
                    final Envelop envelop = res.body();
                    // 6. Invoke
                    final Envelop reply =
                            Instance.invoke(reference, method.getName(), envelop);
                    // 7. Reply Message
                    res.reply(reply);
                });
            }, address, reference, method);
        }
    }

    private void verify(final Method method) {
        // 1. Ensure method length.
    }
}
