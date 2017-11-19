package io.vertx.up.web.parallel;

import io.vertx.zero.func.refine.UpConsumer;
import io.vertx.zero.log.Annal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadQueue {

    private static final Annal LOGGER = Annal.get(ThreadQueue.class);
    private final CountDownLatch counter;
    private final List<Thread> threads = new ArrayList<>();

    public ThreadQueue(final int size) {
        this.counter = new CountDownLatch(size);
    }

    public void add(final UpConsumer runnable,
                    final String name) {
        final Thread thread = new ThreadAtom(this.counter, runnable);
        thread.setName(name);
        this.threads.add(thread);
    }

    public void startSync() {
        startAsync();
        try {
            this.counter.await();
        } catch (final InterruptedException ex) {
            LOGGER.jvm(ex);
        }
    }

    public void startAsync() {
        for (final Thread thread : this.threads) {
            thread.start();
        }
    }
}
