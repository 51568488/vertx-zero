package io.vertx.zero.exception;

import java.lang.reflect.Method;

public class WorkerArgumentException extends UpException {

    public WorkerArgumentException(final Class<?> clazz,
                                   final Method method) {
        super(clazz, method.getName(), method.getDeclaringClass().getName());
    }

    @Override
    public int getCode() {
        return -40017;
    }
}
