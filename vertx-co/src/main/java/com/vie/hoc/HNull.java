package com.vie.hoc;

import com.vie.fun.error.JdConsumer;
import com.vie.fun.lang.JcConsumer;
import com.vie.exception.ZeroException;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class HNull {
    /**
     * If every element is not null, execute fnExec.
     *
     * @param fnExec
     * @param input
     */
    public static void execZero(final JdConsumer fnExec, final Object... input)
            throws ZeroException {
        if (0 == input.length) {
            fnExec.exec();
        } else {
            final boolean match =
                    Arrays.stream(input).allMatch(HNull::not);
            if (match) {
                fnExec.exec();
            }
        }
    }

    /**
     * If input is not null, execute fnExec.
     * fnExec: (t) -> { }
     *
     * @param input
     * @param fnExec
     * @param <T>
     */
    public static <T> void exec(final Consumer<T> fnExec, final Object input) {
        if (null != input) {
            final T cast = (T) input;
            fnExec.accept(cast);
        }
    }

    /**
     * If every element is not null, execute fnExec.
     * fnExec: () -> {}
     *
     * @param fnExec
     * @param input
     */
    public static void exec(final JcConsumer fnExec, final Object... input) {
        if (0 < input.length) {
            final boolean match =
                    Arrays.stream(input).allMatch(HNull::not);
            if (match) {
                fnExec.exec();
            }
        } else {
            // Not need to check
            fnExec.exec();
        }
    }

    /**
     * If every element is not null, execute fnGet.
     * fnGet: () -> T
     *
     * @param fnGet
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T get(final Supplier<T> fnGet, final Object... input) {
        T reference = null;
        if (0 < input.length) {
            final boolean match = Arrays.stream(input).allMatch(HNull::not);
            if (match) {
                reference = fnGet.get();
            }
        }
        return reference;
    }

    /**
     * If reference = null, return dft directly.
     * If fnGet return value is null, check with dft.
     *
     * @param reference
     * @param fnGet
     * @param dft
     * @param <T>
     * @return
     */
    public static <T> T get(final Object reference,
                            final Supplier<T> fnGet,
                            final T dft) {
        if (null == reference) {
            return dft;
        }
        final T ret = fnGet.get();
        return (null == ret) ? dft : ret;
    }

    /**
     * If reference is null, throw ZeroException.
     *
     * @param <T>
     * @return
     * @throws ZeroException
     */
    public static <T> T get(final T reference,
                            final ZeroException error)
            throws ZeroException {
        if (null == reference) {
            throw error;
        }
        return reference;
    }

    /**
     * Function chain.
     *
     * @param reference
     * @param tranFn
     * @param nextFn
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> T chain(final F reference,
                                 final Function<F, T> tranFn,
                                 final Supplier<T> nextFn) {
        if (null != reference) {
            return tranFn.apply(reference);
        } else {
            return nextFn.get();
        }
    }

    /**
     * @param item
     * @return
     */
    public static boolean is(final Object item) {
        return null == item;
    }

    /**
     * @param item
     * @return
     */
    public static boolean not(final Object item) {
        return !is(item);
    }
}
