package io.vertx.up.web.thread;

import io.vertx.up.atom.Event;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.EventExtractor;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class EndPointThread extends Thread {

    private static final Annal LOGGER = Annal.get(EndPointThread.class);

    private final Set<Event> events = new HashSet<>();

    private final transient CountDownLatch countDownLatch;

    private final transient Extractor<Set<Event>> extractor =
            Instance.instance(EventExtractor.class);

    private final transient Class<?> reference;

    public EndPointThread(final Class<?> clazz,
                          final CountDownLatch countDownLatch) {
        this.setName("zero-endpoint-scanner-" + this.getId());
        this.reference = clazz;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        if (null != this.reference) {
            this.events.addAll(this.extractor.extract(this.reference));
            LOGGER.info(Info.SCANED_EVENTS, this.reference.getName(),
                    this.events.size());
            this.countDownLatch.countDown();
        }
    }

    public Set<Event> getEvents() {
        return this.events;
    }
}
