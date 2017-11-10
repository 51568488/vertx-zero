package org.vie.util;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.vie.fun.HFail;
import org.vie.fun.HNull;
import org.vie.fun.HPool;

import java.lang.reflect.Constructor;

@SuppressWarnings("unchecked")
public final class Instance {
    /**
     * Create new instance with reflection
     *
     * @param name
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T instance(final String name,
                                 final Object... params) {
        return instance(clazz(name), params);
    }

    public static <T> T instance(final Class<?> clazz,
                                 final Object... params) {
        final Object created = HFail.exec(
                () -> construct(clazz, params), clazz);
        return HFail.exec(() -> (T) created, created);
    }

    /**
     * Singleton instances
     *
     * @param name
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T singleton(final String name,
                                  final Object... params) {
        return singleton(clazz(name), params);
    }

    public static <T> T singleton(final Class<?> clazz,
                                  final Object... params) {
        final Object created = HPool.exec(Storage.SINGLETON, clazz.getName(),
                () -> instance(clazz, params));
        // Must reference to created first.
        return HFail.exec(() -> (T) created, created);
    }

    /**
     * Get class from name, cached into memory pool
     *
     * @param name
     * @return
     */
    public static Class<?> clazz(final String name) {
        return HPool.exec(Storage.CLASSES, name,
                () -> HFail.exec(() -> Class.forName(name), name));
    }

    /**
     * Method reflection call
     *
     * @param instance
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T invoke(final Object instance,
                               final String name,
                               final Object... args) {
        return HNull.get(() -> {
            final MethodAccess access = MethodAccess.get(instance.getClass());
            final Object ret = access.invoke(instance, name, args);
            return HNull.get(() -> (T) ret, ret);
        }, instance, name);
    }

    /**
     * Whether the class contains no-arg constructor
     *
     * @param clazz
     * @return
     */
    public static boolean noarg(final Class<?> clazz) {
        return HNull.get(clazz, () -> {
            final Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            boolean noarg = false;
            for (final Constructor<?> constructor : constructors) {
                if (0 == constructor.getParameterTypes().length) {
                    noarg = true;
                }
            }
            return noarg;
        }, false);
    }

    private static <T> T construct(final Class<?> clazz,
                                   final Object... params) {
        return HFail.exec(() -> {
            T ret = null;
            final Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            // Pack all constructors
            for (final Constructor<?> constructor : constructors) {
                // Fast to construct
                if (0 == params.length) {
                    ret = (T) construct(clazz);
                }
                // Fast to compare argument length
                if (params.length != constructor.getParameterTypes().length) {
                    continue;
                }
                // The slowest construct
                final Object reference = constructor.newInstance(params);
                ret = HFail.exec(() -> ((T) reference), reference);
            }
            return ret;
        }, clazz, params);
    }

    private static <T> T construct(final Class<T> clazz) {
        return HFail.exec(() -> {
            // Reflect Asm
            final ConstructorAccess<T> access = ConstructorAccess.get(clazz);
            return access.newInstance();
        }, clazz);
    }

    private Instance() {
    }
}
