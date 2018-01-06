package io.vertx.up.micro.ipc.tower;

import io.vertx.core.Future;
import io.vertx.up.atom.Envelop;
import io.vertx.up.func.Fn;
import io.vertx.up.tool.mirror.Instance;

import java.lang.reflect.Method;

/**
 * The last point for method
 */
public class FinalTransit implements Transit {

    private transient Method method;

    @Override
    public Future<Envelop> async(final Envelop data) {
        // 1. Extract type
        final Object proxy = Instance.singleton(this.method.getDeclaringClass());
        // 2. Async type
        return ReturnTransit.build(
                Fn.getJvm(() -> this.method.invoke(proxy, data), this.method)
        );
    }

    @Override
    public Transit connect(final Method method) {
        this.method = method;
        return this;
    }
}
