package io.vertx.up.func;

import io.vertx.core.VertxException;
import io.vertx.up.log.Annal;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.ZeroRunException;

/**
 * Defend means swapper the exception part for specific statement.
 * Uniform to manage exception code flow.
 */
class Defend {
    /**
     * Execute without any return type
     *
     * @param actuator
     * @param logger
     */
    static void jvmVoid(final JvmActuator actuator,
                        final Annal logger) {
        try {
            actuator.execute();
        } catch (final Throwable ex) {
            if (null != logger) {
                logger.jvm(ex);
            }
        }
    }

    /**
     * Execute with return T
     *
     * @param supplier
     * @param logger
     * @param <T>
     * @return
     */
    static <T> T jvmReturn(final JvmSupplier<T> supplier,
                           final Annal logger) {
        T reference = null;
        try {
            reference = supplier.get();
        } catch (final Exception ex) {
            if (null != logger) {
                logger.jvm(ex);
            }
        }
        return reference;
    }

    /**
     * @param actuator
     * @param logger
     */
    static void zeroVoid(final ZeroActuator actuator,
                         final Annal logger) {
        try {
            actuator.execute();
        } catch (final ZeroException ex) {
            if (null != logger) {
                logger.zero(ex);
            }
        } catch (final VertxException ex) {
            if (null != logger) {
                logger.vertx(ex);
            }
        } catch (final Throwable ex) {
            if (null != logger) {
                logger.jvm(ex);
            }
        }
    }

    /**
     * @param supplier
     * @param logger
     * @param <T>
     * @return
     */
    static <T> T zeroReturn(final ZeroSupplier<T> supplier,
                            final Annal logger) {
        T ret = null;
        try {
            ret = supplier.get();
        } catch (final ZeroException ex) {
            if (null != logger) {
                logger.zero(ex);
            }
        } catch (final ZeroRunException ex) {
            if (null != logger) {
                logger.vertx(ex);
                throw ex;
            }
        } catch (final VertxException ex) {
            if (null != logger) {
                logger.vertx(ex);
            }
        } catch (final Throwable ex) {
            if (null != logger) {
                logger.jvm(ex);
            }
        }
        return ret;
    }
}
