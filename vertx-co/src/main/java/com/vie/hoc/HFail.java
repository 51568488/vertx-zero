package com.vie.hoc;

import com.vie.fun.error.JeSupplier;
import com.vie.exception.ZeroException;
import com.vie.exception.ZeroRunException;
import com.vie.log.Annal;

import java.util.Arrays;

public class HFail {

    private static final Annal LOGGER = Annal.get(HFail.class);

    public static <T> T exec(final JeSupplier<T> supplier,
                             final Object... input) {
        return execDft(supplier, null, input);
    }

    public static <T> T execDft(final JeSupplier<T> supplier,
                                final T dft,
                                final Object... input) {
        T ret = null;
        try {
            final boolean match = Arrays.stream(input).allMatch(HNull::not);
            if (match) {
                ret = supplier.get();
                if (null == ret) {
                    ret = dft;
                }
            }
        } catch (final ZeroException ex) {
            LOGGER.zero(ex);
        } catch (final ZeroRunException ex) {
            // Throw out customed exception only.
            throw ex;
        } catch (final Exception ex) {
            LOGGER.jvm(ex);
        }
        return ret;
    }
}
