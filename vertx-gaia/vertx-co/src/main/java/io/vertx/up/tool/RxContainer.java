package io.vertx.up.tool;

import io.vertx.up.func.Fn;

public class RxContainer {

    private Object reference;

    public <T> RxContainer add(final T reference) {
        this.reference = reference;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
        return Fn.get(() -> (T) this.reference, this.reference);
    }
}
