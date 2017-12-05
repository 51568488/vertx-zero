package io.vertx.up.tool;

public final class Runner {

    public static void run(final Runnable hooker,
                           final String name) {
        final Thread thread = new Thread(hooker);
        thread.setName(name);
        thread.start();
    }
}
