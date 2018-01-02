package io.vertx.up.tool;

import java.util.function.BiFunction;

/**
 * For comparing
 */
public class Compare {

    public static int compareTo(final int left, final int right) {
        return left - right;
    }

    public static int compareTo(final String left, final String right) {
        return compareTo(left, right,
                (leftVal, rightVal) -> leftVal.compareTo(rightVal));
    }

    public static <T> int compareTo(
            final T left, final T right,
            final BiFunction<T, T, Integer> compare) {
        if (null == left && null == right) {
            return 0;
        } else if (null == left && null != right) {
            return -1;
        } else if (null != left && null == right) {
            return 1;
        } else {
            return compare.apply(left, right);
        }
    }
}
