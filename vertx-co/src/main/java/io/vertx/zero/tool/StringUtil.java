package io.vertx.zero.tool;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.core.json.JsonObject;
import io.vertx.up.func.Fn;
import io.vertx.zero.eon.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lang
 */
public class StringUtil {

    public static String from(final Object value) {
        return null == value ? Strings.EMPTY : value.toString();
    }

    public static String from(final JsonObject value) {
        return null == value ? Strings.EMPTY : value.toString();
    }

    public static Set<String> split(final String input, final String separator) {
        return Fn.obtain(() -> {
            final String[] array = input.split(separator);
            final Set<String> result = new ConcurrentHashSet<>();
            for (final String item : array) {
                Fn.safeNull(() -> result.add(item.trim().intern()), item);
            }
            return result;
        }, input, separator);
    }

    public static String join(final Set<String> input) {
        return join(input, null);
    }

    public static String join(final Set<String> input, final String separator) {
        final String connector = (null == separator) ? Strings.COMMA : separator;
        return Fn.obtain(() -> {
            final StringBuilder builder = new StringBuilder();
            final int size = input.size();
            int start = 0;
            for (final String item : input) {
                builder.append(item);
                start++;
                if (start < size) {
                    builder.append(connector);
                }
            }
            return builder.toString();
        }, input);
    }

    public static String join(final Object[] input) {
        final Set<String> data = new HashSet<>();
        for (final Object item : input) {
            if (null != item) {
                data.add(item.toString());
            }
        }
        return join(data);
    }

    public static boolean isNil(final String input) {
        return null == input || 0 == input.trim().length();
    }
}
