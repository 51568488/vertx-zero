package io.vertx.up.tool;

import io.vertx.up.func.Fn;

/**
 * Single Rxjava container for null pointer that is not used in
 * Rxjava2.
 */
public class RxHod {

    private Object reference;

    public <T> RxHod add(final T reference) {
        this.reference = reference;
        return this;
    }

    public boolean successed() {
        return null != this.reference;
    }

    @SuppressWarnings("unchecked")
    public <T> T get() {
        return Fn.get(() -> (T) this.reference, this.reference);
    }
}
