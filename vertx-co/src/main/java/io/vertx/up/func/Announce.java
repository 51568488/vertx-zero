package io.vertx.up.func;

import io.vertx.up.log.Annal;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.ZeroRunException;
import io.vertx.zero.tool.mirror.Instance;

/**
 * Announce means tell every one of Zero system that there occurs error, the error contains
 * 1. java.lang.Exception ( Checked )
 * 2. io.vertx.zero.exception.ZeroException ( Checked )
 * 3. java.lang.Throwable ( Runtime )
 * 4. io.vertx.zero.exception.ZeroRunException ( Runtime )
 */
class Announce {
    /**
     * Build zero error and throw ZeroException out.
     *
     * @param logger
     * @param zeroClass
     * @param args
     * @throws ZeroException
     */
    static void outZero(final Annal logger,
                        final Class<? extends ZeroException> zeroClass,
                        final Object... args)
            throws ZeroException {
        final ZeroException error = Instance.instance(zeroClass, args);
        if (null != error) {
            if (null != logger) {
                logger.zero(error);
            }
            throw error;
        }
    }

    /**
     * Build up error and throw ZeroRunException out.
     *
     * @param logger
     * @param upClass
     * @param args
     */
    static void outUp(final Annal logger,
                      final Class<? extends ZeroRunException> upClass,
                      final Object... args) {
        final ZeroRunException error = Instance.instance(upClass, args);
        if (null != error) {
            if (null != logger) {
                logger.vertx(error);
            }
            throw error;
        }
    }

    /**
     * Execut actuator and throw ZeroRunException out
     *
     * @param actuator
     * @param logger
     */
    static void shuntRun(final Actuator actuator,
                         final Annal logger) {
        try {
            actuator.execute();
        } catch (final ZeroRunException ex) {
            if (null != logger) {
                logger.vertx(ex);
                throw ex;
            }
        } catch (final Throwable ex) {
            logger.jvm(ex);
        }
    }
}
