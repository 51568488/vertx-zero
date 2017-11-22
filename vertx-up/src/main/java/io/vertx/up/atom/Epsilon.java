package io.vertx.up.atom;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Parameter container to get parameters
 */
public class Epsilon<T> implements Serializable {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    private Class<?> argType;

    private Annotation[] annotations;

    private T value;

    public Class<?> getArgType() {
        return this.argType;
    }

    public void setArgType(final Class<?> argType) {
        this.argType = argType;
    }

    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public void setAnnotations(final Annotation[] annotations) {
        this.annotations = annotations;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Epsilon{" +
                "name='" + this.name + '\'' +
                ", argType=" + this.argType +
                ", annotations=" + Arrays.toString(this.annotations) +
                ", value=" + this.value +
                '}';
    }
}
