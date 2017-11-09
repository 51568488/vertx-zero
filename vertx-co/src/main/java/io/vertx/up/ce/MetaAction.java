package io.vertx.up.ce;

import java.lang.reflect.Method;

/**
 * Metadata action
 */
public class MetaAction {

    private Method method;

    private Class<?> clazz;

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(final Method method) {
        this.method = method;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public void setClazz(final Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MetaAction that = (MetaAction) o;

        if (!this.method.equals(that.method)) {
            return false;
        }
        return this.clazz.equals(that.clazz);
    }

    @Override
    public int hashCode() {
        int result = this.method.hashCode();
        result = 31 * result + this.clazz.hashCode();
        return result;
    }
}
