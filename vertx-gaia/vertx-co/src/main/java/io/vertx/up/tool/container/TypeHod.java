package io.vertx.up.tool.container;

import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@SuppressWarnings("all")
public class TypeHod<T> implements Comparable<TypeReference<T>> {
    protected final Type _type;

    protected TypeHod() {
        final Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        } else {
            this._type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        }
    }

    public Type getType() {
        return this._type;
    }

    @Override
    public int compareTo(final TypeReference<T> o) {
        return 0;
    }
}
