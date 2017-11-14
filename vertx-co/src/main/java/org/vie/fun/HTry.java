package org.vie.fun;

import io.vertx.core.VertxException;
import io.vertx.exception.ZeroException;
import io.vertx.exception.ZeroRunException;
import org.vie.fun.error.JdConsumer;
import org.vie.fun.error.JdSupplier;
import org.vie.fun.error.JeSupplier;
import org.vie.fun.lang.JcConsumer;
import org.vie.util.log.Annal;

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
     * @param supplier
     * @param logger
     */
    public static void execJvm(final JeSupplier<Void> supplier,
                               final Annal logger) {
        try {
            supplier.get();
        } catch (final Exception ex) {
            logger.jvm(ex);
        }
    }

    /**
     * @param supplier
     * @param logger
     * @param <T>
     * @return
     */
    public static <T> T execGet(final JeSupplier<T> supplier,
                                final Annal logger) {
        T reference = null;
        try {
            reference = supplier.get();
        } catch (final Exception ex) {
            logger.jvm(ex);
        }
        return reference;
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
