package io.vertx.up.concurrent;

import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public final class Runner {

    private static final Annal LOGGER = Annal.get(Runner.class);

    public static void run(final Runnable hooker,
                           final String name) {
        final Thread thread = new Thread(hooker);
        thread.setName(name);
        thread.start();
    }

    public static <T> void run(final List<Routine<T>> routines,
                               final ConcurrentMap<String, T> result) {
        final List<Thread> references = new ArrayList<>();
        for (final Routine<T> routine : routines) {
            final Thread thread = new Thread(routine);
            references.add(thread);
            thread.start();
        }
        references.forEach(item -> {
            try {
                item.join();
            } catch (final InterruptedException ex) {
                LOGGER.jvm(ex);
            }
        });
        for (final Routine<T> routine : routines) {
            final String key = routine.getKey();
            final T value = routine.get();
            Fn.safeNull(() -> {
                result.put(key, value);
            }, key, value);
        }
    }
}
