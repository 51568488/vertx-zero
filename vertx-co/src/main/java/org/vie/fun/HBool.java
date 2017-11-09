package org.vie.fun;

import org.vie.exception.ZeroException;
import org.vie.exception.ZeroRunException;
import org.vie.fun.error.JdSupplier;
import org.vie.fun.error.JeSupplier;
import org.vie.util.Instance;
import org.vie.util.log.Annal;

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
                                 final JeSupplier<T> supplier) {
        T ret = null;
        if (condition) {
            try {
                ret = supplier.get();
            } catch (final Exception ex) {

            }
        }
        return ret;
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
        return HTry.execZero(() -> execZero(condition,
                tSupplier::get, fSupplier::get), LOGGER);
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
                                 final JdSupplier<T> tSupplier,
                                 final JdSupplier<T> fSupplier)
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

    /**
     * @param condition
     * @param logger
     * @param errorCls
     * @param args
     */
    public static void execUp(final boolean condition,
                              final Annal logger,
                              final Class<? extends ZeroRunException> errorCls,
                              final Object... args) {
        if (condition) {
            HNull.exec(() -> {
                final ZeroRunException error =
                        Instance.instance(errorCls, args);
                logger.vertx(error);
                throw error;
            }, logger, errorCls);
        }
    }
}
