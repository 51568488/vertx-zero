package org.vie.util;

import io.vertx.core.VertxException;
import io.vertx.core.logging.Logger;
import io.vertx.exception.ZeroException;
import org.vie.fun.HNull;
import org.vie.fun.lang.JcPredicate;

import java.text.MessageFormat;
import java.util.function.Consumer;

public final class Log {

    public static void jvm(final Logger logger, final Throwable ex) {
        HNull.exec(logger::warn, ex);
        ex.printStackTrace();
    }

    public static void zero(final Logger logger, final ZeroException ex) {
        HNull.exec(logger::warn, ex);
    }

    public static void vertx(final Logger logger, final VertxException ex) {
        HNull.exec(logger::warn, ex);
    }

    public static void info(final Logger logger, final String pattern, final Object... rest) {
        log(logger::isInfoEnabled, logger::info, pattern, rest);
    }

    public static void debug(final Logger logger, final String pattern, final Object... rest) {
        log(logger::isDebugEnabled, logger::debug, pattern, rest);
    }

    public static void warn(final Logger logger, final String pattern, final Object... rest) {
        log(() -> true, logger::warn, pattern, rest);
    }

    public static void error(final Logger logger, final String pattern, final Object... rest) {
        log(() -> true, logger::error, pattern, rest);
    }

    private static void log(final JcPredicate fnPre,
                            final Consumer<Object> fnLog,
                            final String message,
                            final Object... rest) {
        if (fnPre.is()) {
            if (0 < rest.length) {
                fnLog.accept(MessageFormat.format(message, rest));
            } else {
                fnLog.accept(message);
            }
        }
    }
}
