package io.vertx.up.func;

import io.vertx.up.log.Annal;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.ZeroRunException;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
class Zero {
    private static final Annal LOGGER = Annal.get(Zero.class);

    static <T, F> T nullFlow(final F reference,
                             final Function<F, T> tranFn,
                             final Supplier<T> nextFn) {
        if (null != reference) {
            return tranFn.apply(reference);
        } else {
            return nextFn.get();
        }
    }

    static <T> void exec(final Consumer<T> fnExec, final T input) {
        if (null != input) {
            fnExec.accept(input);
        }
    }

    static void exec(final Actuator actuator, final Object... input) {
        if (0 < input.length) {
            final boolean match =
                    Arrays.stream(input).allMatch(Zero::not);
            if (match) {
                actuator.execute();
            }
        } else {
            // Not need to check
            actuator.execute();
        }
    }

    static void execZero(final ZeroActuator actuator, final Object... input)
            throws ZeroException {
        if (0 == input.length) {
            actuator.execute();
        } else {
            final boolean match =
                    Arrays.stream(input).allMatch(Zero::not);
            if (match) {
                actuator.execute();
            }
        }
    }

    static <T> T getJvm(
            final T defaultValue,
            final JvmSupplier<T> supplier,
            final Object... input
    ) {
        T ret = null;
        try {
            final boolean match = Arrays.stream(input).allMatch(Zero::not);
            if (match) {
                ret = supplier.get();
                if (null == ret) {
                    ret = defaultValue;
                }
            }
        } catch (final ZeroException ex) {
            LOGGER.zero(ex);
        } catch (final ZeroRunException ex) {
            // Throw out customed exception only.
            throw ex;
        } catch (final Throwable ex) {
            LOGGER.jvm(ex);
        }
        return ret;
    }

    static <T> T get(final T defaultValue,
                     final Supplier<T> fnGet,
                     final Object... reference) {
        final boolean match =
                Arrays.stream(reference).allMatch(Zero::not);
        if (match) {
            final T ret = fnGet.get();
            return (null == ret) ? defaultValue : ret;
        } else {
            return defaultValue;
        }
    }

    private static boolean is(final Object item) {
        return null == item;
    }

    private static boolean not(final Object item) {
        return !is(item);
    }
}
