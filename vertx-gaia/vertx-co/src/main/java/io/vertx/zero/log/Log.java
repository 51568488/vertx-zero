package io.vertx.zero.log;

import io.vertx.core.VertxException;
import io.vertx.core.logging.Logger;
import io.vertx.up.func.Fn;
import io.vertx.zero.exception.ZeroException;

import java.text.MessageFormat;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class Log {

    public static void jvm(final Logger logger, final Throwable ex) {
        Fn.safeNull(logger::warn, ex);
        ex.printStackTrace();
    }

    public static void zero(final Logger logger, final ZeroException ex) {
        Fn.safeNull(logger::warn, ex);
    }

    public static void vertx(final Logger logger, final VertxException ex) {
        Fn.safeNull(logger::warn, ex);
    }

    public static void info(final Logger logger, final String pattern, final Object... rest) {
        log(logger::isInfoEnabled, logger::info, pattern, rest);
    }

    public static void debug(final Logger logger, final String pattern, final Object... rest) {
        log(() -> true, logger::debug, pattern, rest);
    }

    public static void warn(final Logger logger, final String pattern, final Object... rest) {
        log(() -> true, logger::warn, pattern, rest);
    }

    public static void error(final Logger logger, final String pattern, final Object... rest) {
        log(() -> true, logger::error, pattern, rest);
    }

    private static void log(final Supplier<Boolean> fnPre,
                            final Consumer<Object> fnLog,
                            final String message,
                            final Object... rest) {
        if (fnPre.get()) {
            if (0 < rest.length) {
                fnLog.accept(MessageFormat.format(message, rest));
            } else {
                fnLog.accept(message);
            }
        }
    }
}
