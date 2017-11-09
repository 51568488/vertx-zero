package org.vie.util;

import org.vie.exception.run.ArgumentException;

public final class Ensurer {

    public static void eqLength(
            final Class<?> clazz,
            final int expected,
            final Object... args
    ) {
        if (expected != args.length) {
            final String method = Errors.method(Ensurer.class, "eqLength");
            throw new ArgumentException(clazz, method, expected, "=");
        }
    }

    public static void gtLength(
            final Class<?> clazz,
            final int min,
            final Object... args
    ) {
        if (min >= args.length) {
            final String method = Errors.method(Ensurer.class, "gtLength");
            throw new ArgumentException(clazz, method, min, ">");
        }
    }
}
