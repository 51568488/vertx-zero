package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.log.Annal;

import java.util.Set;

/**
 * Injection system
 */
public class AffluxScatter implements Scatter {

    private static final Annal LOGGER = Annal.get(AffluxScatter.class);

    @Override
    public void connect(final Vertx vertx) {
        // Extract all events.
        final Set<Event> events = ZeroAnno.getEvents();
        for (final Event event : events) {
            inject(event.getProxy());
        }
        // Extract all receipts.
        final Set<Receipt> receipts = ZeroAnno.getReceipts();
        for (final Receipt receipt : receipts) {
            inject(receipt.getProxy());
        }
    }

    private void inject(final Object proxy) {

    }
}
