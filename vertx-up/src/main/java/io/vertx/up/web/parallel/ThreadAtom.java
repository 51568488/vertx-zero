package io.vertx.up.web.parallel;

import io.vertx.zero.func.refine.UpConsumer;

import java.util.concurrent.CountDownLatch;

public class ThreadAtom extends Thread {

    private final transient CountDownLatch counter;
    private final transient UpConsumer consumer;

    public ThreadAtom(final CountDownLatch counter,
                      final UpConsumer consumer) {
        this.counter = counter;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        this.consumer.exec();
        this.counter.countDown();
    }
}
