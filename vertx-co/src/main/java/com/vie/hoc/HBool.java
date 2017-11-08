package com.vie.hoc;

import com.vie.fun.error.JdSupplier;
import com.vie.fun.error.JeSupplier;
import com.vie.exception.ZeroException;
import com.vie.exception.ZeroRunException;
import com.vie.log.Annal;

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
}
