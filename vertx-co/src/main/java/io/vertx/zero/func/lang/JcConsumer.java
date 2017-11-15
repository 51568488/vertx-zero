package io.vertx.zero.func.lang;

/**
 * It's different from Consumer<T>, call the method directly without any params.
 */
@FunctionalInterface
public interface JcConsumer {
    void exec();
}
