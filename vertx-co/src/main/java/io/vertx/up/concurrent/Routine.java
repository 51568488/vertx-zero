package io.vertx.up.concurrent;

/**
 * Routine execute map
 * @param <T>
 */
public interface Routine<T> extends Runnable {
    /**
     * Executed after thread finished.
     * @return
     */
    T get();
    /**
     * Get executed key of this thread.
     * @return
     */
    String getKey();
}
