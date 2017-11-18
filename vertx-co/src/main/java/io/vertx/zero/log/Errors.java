package io.vertx.zero.log;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.eon.Tpl;
import io.vertx.zero.exception.ErrorMissingException;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HFail;
import io.vertx.zero.marshal.node.JObjectBase;
import io.vertx.zero.marshal.node.ZeroError;
import io.vertx.zero.tool.mirror.Instance;

import java.text.MessageFormat;

/**
 *
 */
public final class Errors {

    public static String normalize(final Class<?> clazz,
                                   final int code,
                                   final Object... args) {
        return HFail.exec(() -> {
            final String key = ("E" + Math.abs(code)).intern();
            final JObjectBase node
                    = Instance.singleton(ZeroError.class);
            final JsonObject data = node.read();
            return HBool.exec(null != data && data.containsKey(key),
                    () -> {
                        // 1. Read pattern
                        final String pattern = data.getString(key);
                        // 2. Build message
                        final String error = MessageFormat.format(pattern, args);
                        // 3. Format
                        return MessageFormat.format(
                                Tpl.ZERO_ERROR, String.valueOf(code),
                                clazz.getSimpleName(),
                                error
                        );
                    },
                    new ErrorMissingException(code, clazz.getName()));
        }, clazz);
    }

    public static String method(final Class<?> clazzPos,
                                final String methodPos) {
        final StackTraceElement[] methods = Thread.currentThread().getStackTrace();
        String methodName = null;
        int position = 0;
        for (int idx = 0; idx < methods.length; idx++) {
            final String clazz = methods[idx].getClassName();
            final String method = methods[idx].getMethodName();
            if (clazz.equals(clazzPos.getName())
                    && method.equals(methodPos)) {
                position = idx + 1;
            }
        }
        if (position < methods.length - 1) {
            methodName = methods[position].getMethodName();
        }
        return methodName;
    }
}
