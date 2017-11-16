package io.vertx.up.web.thread;

import io.vertx.up.atom.Receipt;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.ReceiptExtractor;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class QueueThread extends Thread {

    private static final Annal LOGGER = Annal.get(QueueThread.class);

    private final Set<Receipt> receipts = new HashSet<>();

    private final CountDownLatch countDownLatch;

    private final Extractor<Set<Receipt>> extractor =
            Instance.instance(ReceiptExtractor.class);

    private final Class<?> reference;

    public QueueThread(final Class<?> clazz,
                       final CountDownLatch countDownLatch) {
        this.setName("zero-queue-scanner-" + this.getId());
        this.reference = clazz;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if (null != this.reference) {
            this.receipts.addAll(this.extractor.extract(this.reference));
            LOGGER.info(Info.SCANED_RECEIPTS, this.reference.getName(),
                    this.receipts.size());
            this.countDownLatch.countDown();
        }
    }

    public Set<Receipt> getReceipts() {
        return this.receipts;
    }
}
