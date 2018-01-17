package io.vertx.up.tool.container;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

@SuppressWarnings("all")
public class TypeHod<T> implements Comparable<TypeHod<T>> {
    protected Type _type;

    protected TypeHod() {
        final Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        } else {
            this._type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        }
    }

    public Type getType() {
        // Fix issue: sun.reflect.generics.reflectiveObjects.TypeVariableImpl cannot be cast to java.lang.Class
        if (this._type instanceof TypeVariable) {
            this._type = ((TypeVariable) this._type).getBounds()[0].getClass();
        }
        return this._type;
    }

    @Override
    public int compareTo(final TypeHod<T> o) {
        return 0;
    }
}
