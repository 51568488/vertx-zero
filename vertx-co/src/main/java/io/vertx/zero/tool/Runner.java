package io.vertx.zero.tool;

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public final class Runner {

    public static void run(final Runnable hooker,
                           final String name) {
        final ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat(name).build();
        final ExecutorService executor = Executors.newCachedThreadPool(factory);
        executor.execute(hooker);
        executor.shutdown();
    }
}
