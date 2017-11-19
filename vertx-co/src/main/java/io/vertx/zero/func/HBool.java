package io.vertx.zero.func;

import io.vertx.up.func.Fn;
import io.vertx.up.func.JvmSupplier;
import io.vertx.up.func.ZeroSupplier;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.ZeroRunException;
import io.vertx.zero.func.refine.UpConsumer;
import io.vertx.zero.log.Annal;

import java.util.function.Supplier;

public class HBool {
    private static final Annal LOGGER = Annal.get(HBool.class);

    /**
     * @param condition
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> T execTrue(final boolean condition,
                                 final JvmSupplier<T> supplier) {
        T ret = null;
        if (condition) {
            try {
                ret = supplier.get();
            } catch (final Exception ex) {
                LOGGER.jvm(ex);
            }
        }
        return ret;
    }

    public static void execTrue(final boolean condition,
                                final UpConsumer consumer) {
        if (condition) {
            consumer.exec();
        }
    }

    /**
     * @param condition
     * @param supplier
     * @param exp
     * @param <T>
     * @return
     */
    public static <T> T exec(final boolean condition,
                             final Supplier<T> supplier,
                             final ZeroRunException exp) {
        if (condition) {
            return supplier.get();
        } else {
            throw exp;
        }
    }

    /**
     * @param condition
     * @param tSupplier
     * @param fSupplier
     * @param <T>
     * @return
     */
    public static <T> T exec(final boolean condition,
                             final Supplier<T> tSupplier,
                             final Supplier<T> fSupplier) {
        return Fn.safeZero(() -> execZero(condition,
                tSupplier::get, fSupplier::get), LOGGER);
    }

    /**
     * Common work flow
     *
     * @param condition
     * @param logger
     * @param tSupplier
     * @param fSupplier
     */
    public static void exec(final boolean condition,
                            final Annal logger,
                            final UpConsumer tSupplier,
                            final UpConsumer fSupplier) {
        Fn.safeZero(() -> execZero(condition,
                () -> {
                    if (null != tSupplier) {
                        tSupplier.exec();
                    }
                    return null;
                }, () -> {
                    if (null != fSupplier) {
                        fSupplier.exec();
                    }
                    return null;
                }), logger);
    }

    /**
     * @param condition
     * @param tSupplier
     * @param fSupplier
     * @param <T>
     * @return
     * @throws ZeroException
     */
    public static <T> T execZero(final boolean condition,
                                 final ZeroSupplier<T> tSupplier,
                                 final ZeroSupplier<T> fSupplier)
            throws ZeroException {
        T ret = null;
        if (condition) {
            if (null != tSupplier) {
                ret = tSupplier.get();
            }
        } else {
            if (null != fSupplier) {
                ret = fSupplier.get();
            }
        }
        return ret;
    }
}
