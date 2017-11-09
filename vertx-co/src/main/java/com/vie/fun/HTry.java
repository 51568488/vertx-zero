package com.vie.fun;

import com.vie.exception.ZeroException;
import com.vie.exception.ZeroRunException;
import com.vie.fun.error.JdConsumer;
import com.vie.fun.error.JdSupplier;
import com.vie.fun.lang.JcConsumer;
import com.vie.util.log.Annal;
import io.vertx.core.VertxException;

import java.util.function.Function;
import java.util.function.Supplier;

public class HTry {
    /**
     * Special try catch for throwable
     *
     * @param supplier
     * @param fnExp
     * @param <T>
     * @return
     */
    public static <T> T exec(final Supplier<T> supplier,
                             final Function<Throwable, ZeroRunException> fnExp) {
        final T ret;
        try {
            ret = supplier.get();
        } catch (final Throwable ex) {
            throw fnExp.apply(ex);
        }
        return ret;
    }

    /**
     * Exec runtime exception
     *
     * @param consumer
     * @param logger
     */
    public static void execUp(final JcConsumer consumer,
                              final Annal logger) {
        try {
            consumer.exec();
        } catch (final ZeroRunException ex) {
            logger.vertx(ex);
            throw ex;
        } catch (final Throwable ex) {
            logger.jvm(ex);
        }
    }

    /**
     * @param consumer
     * @param logger
     */
    public static void exec(final JdConsumer consumer, final Annal logger) {
        try {
            consumer.exec();
        } catch (final ZeroException ex) {
            logger.zero(ex);
        } catch (final VertxException ex) {
            logger.vertx(ex);
        } catch (final Throwable ex) {
            logger.jvm(ex);
        }
    }

    /**
     * Zero for exception transform
     *
     * @param supplier
     * @param logger
     * @param <T>
     * @return
     */
    public static <T> T execZero(final JdSupplier<T> supplier, final Annal logger) {
        T ret = null;
        try {
            ret = supplier.get();
        } catch (final ZeroException ex) {
            logger.zero(ex);
        } catch (final VertxException ex) {
            logger.vertx(ex);
        } catch (final Throwable ex) {
            logger.jvm(ex);
        }
        return ret;
    }

    /**
     * Zero for exception throw out
     *
     * @param logger
     */
    public static void execZero(final Annal logger,
                                final ZeroException error)
            throws ZeroException {
        if (null != error) {
            logger.zero(error);
            throw error;
        }
    }
}
