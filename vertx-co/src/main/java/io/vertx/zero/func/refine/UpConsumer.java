package io.vertx.zero.func.refine;

/**
 * It's different from Consumer<T>, call the method directly without any params.
 */
@FunctionalInterface
public interface UpConsumer {
    void exec();
}
