package io.vertx.up.web.origin;

import io.vertx.up.atom.Receipt;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.web.thread.QueueThread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @Receipt
 */
public class ReceiptInquirer implements Inquirer<Set<Receipt>> {

    private static final Annal LOGGER = Annal.get(ReceiptInquirer.class);

    @Override
    public Set<Receipt> scan(final Set<Class<?>> queues) {
        final CountDownLatch counter = new CountDownLatch(queues.size());
        final List<QueueThread> threadReference = new ArrayList<>();
        /** 3.1. Build Metadata **/
        for (final Class<?> queue : queues) {
            final QueueThread thread =
                    new QueueThread(queue, counter);
            threadReference.add(thread);
            thread.start();
        }
        final Set<Receipt> receipts = new HashSet<>();
        Fn.safeJvm(() -> {
            counter.await();
            for (final QueueThread item : threadReference) {
                receipts.addAll(item.getReceipts());
            }
        }, LOGGER);
        return receipts;
    }
}
