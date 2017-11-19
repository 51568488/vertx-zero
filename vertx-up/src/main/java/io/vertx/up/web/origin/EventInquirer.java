package io.vertx.up.web.origin;

import io.vertx.up.atom.Event;
import io.vertx.up.func.Fn;
import io.vertx.up.web.thread.EndPointThread;
import io.vertx.zero.log.Annal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @Event
 */
public class EventInquirer implements Inquirer<Set<Event>> {

    private static final Annal LOGGER = Annal.get(EventInquirer.class);

    @Override
    public Set<Event> scan(final Set<Class<?>> endpoints) {
        final CountDownLatch counter = new CountDownLatch(endpoints.size());
        final List<EndPointThread> threadReference = new ArrayList<>();
        /** 2.1.Build Api metadata **/
        for (final Class<?> endpoint : endpoints) {
            final EndPointThread thread =
                    new EndPointThread(endpoint, counter);
            threadReference.add(thread);
            thread.start();
        }
        final Set<Event> events = new HashSet<>();
        Fn.safeJvm(() -> {
            counter.await();
            for (final EndPointThread item : threadReference) {
                events.addAll(item.getEvents());
            }
        }, LOGGER);
        return events;
    }
}
