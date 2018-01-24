package io.vertx.up.tool.mirror;

import com.esotericsoftware.reflectasm.MethodAccess;
import io.vertx.up.func.Fn;

import java.util.ArrayList;
import java.util.List;

/**
 * Call interface method by cglib
 */
@SuppressWarnings("unchecked")
class Fantam {

    static <T> T invokeObject(
            final Object instance,
            final String name,
            final Object... args) {
        return Fn.get(() -> {
            final MethodAccess access = MethodAccess.get(instance.getClass());
            // Direct invoke, multi overwrite for unbox/box issue still existing.
            // TODO: Unbox/Box type issue
            Object result;
            try {
                result = access.invoke(instance, name, args);
            } catch (final Throwable ex) {
                ex.printStackTrace();
                // Could not call, re-find the method by index
                // Search method by argument index because could not call directly
                final int index;
                final List<Class<?>> types = new ArrayList<>();
                for (final Object arg : args) {
                    types.add(Types.toPrimary(arg.getClass()));
                }
                index = access.getIndex(name, types.toArray(new Class<?>[]{}));
                result = access.invoke(instance, index, args);
            }
            final Object ret = result;
            return Fn.get(() -> (T) ret, ret);
        }, instance, name);
    }

    static <T> T invokeInterface(
            final Class<?> interfaceCls,
            final String name,
            final Object... args) {
        final Object delegate = getProxy(interfaceCls);
        return Fn.getJvm(() -> invokeObject(delegate, name, args), delegate);
    }

    static <T> T getProxy(
            final Class<?> interfaceCls
    ) {
        return Fn.get(() -> {
            // TODO: Generate interface proxy
            
            return null;
        }, interfaceCls);
    }
}
