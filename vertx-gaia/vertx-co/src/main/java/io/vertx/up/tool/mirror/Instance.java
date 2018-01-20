package io.vertx.up.tool.mirror;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.zero.eon.Values;
import io.vertx.zero.exception.DuplicatedImplException;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked"})
public final class Instance {

    private static final Annal LOGGER = Annal.get(Instance.class);

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
        final Object created = Fn.getJvm(
                () -> construct(clazz, params), clazz);
        return Fn.getJvm(() -> (T) created, created);
    }

    /**
     * Generic type
     *
     * @param target
     * @return
     */
    public static <T> Class<?> genericT(final Class<?> target) {
        return Fn.getJvm(() -> {
            final Type type = target.getGenericSuperclass();
            final Class<T> clazz = (Class) (((ParameterizedType) type).getActualTypeArguments()[0]);
            return clazz;
        }, target);
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
        final Object created = Fn.pool(Storage.SINGLETON, clazz.getName(),
                () -> instance(clazz, params));
        // Must reference to created first.
        return Fn.getJvm(() -> (T) created, created);
    }

    /**
     * Get class from name, cached into memory pool
     *
     * @param name
     * @return
     */
    public static Class<?> clazz(final String name) {
        return Fn.pool(Storage.CLASSES, name,
                () -> Fn.getJvm(() -> Thread
                        .currentThread()
                        .getContextClassLoader()
                        .loadClass(name), name));
    }

    /**
     * Check whether clazz implement the interfaceCls
     *
     * @param clazz
     * @param interfaceCls
     * @return
     */
    public static boolean isMatch(final Class<?> clazz, final Class<?> interfaceCls) {
        final Class<?>[] interfaces = clazz.getInterfaces();
        return Arrays.stream(interfaces)
                .anyMatch(item -> item.equals(interfaceCls));
    }

    public static <T> T invoke(final Object instance,
                               final String name,
                               final Object... args) {
        return Fantam.invokeObject(instance, name, args);
    }

    public static <T> T invoke(final Class<?> interfaceCls,
                               final String name,
                               final Object... args) {
        return Fantam.invokeInterface(interfaceCls, name, args);
    }

    public static <T> T getProxy(
            final Method method) {
        final Class<?> interfaceCls = method.getDeclaringClass();
        return Fantam.getProxy(interfaceCls);
    }

    public static <T> void set(final Object instance,
                               final String name,
                               final T value) {
        Fn.safeNull(() -> Fn.safeJvm(() -> {
            final Field field = instance.getClass().getDeclaredField(name);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(instance, value);
        }, LOGGER), instance, name, value);
    }

    public static <T> T get(final Object instance,
                            final String name) {
        return Fn.get(() -> Fn.safeJvm(() -> {
                    final Field field = instance.getClass().getDeclaredField(name);
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final Object result = field.get(instance);
                    if (null != result) {
                        return (T) result;
                    } else {
                        return null;
                    }
                }, LOGGER)
                , instance, name);
    }

    /**
     * Whether the class contains no-arg constructor
     *
     * @param clazz
     * @return
     */
    public static boolean noarg(final Class<?> clazz) {
        return Fn.get(Boolean.FALSE, () -> {
            final Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            boolean noarg = false;
            for (final Constructor<?> constructor : constructors) {
                if (0 == constructor.getParameterTypes().length) {
                    noarg = true;
                }
            }
            return noarg;
        }, clazz);
    }

    /**
     * Find the unique implementation for interfaceCls
     *
     * @param interfaceCls
     * @return
     */
    public static Class<?> uniqueChild(final Class<?> interfaceCls) {
        return Fn.get(null, () -> {
            final Set<Class<?>> classes = Pack.getClasses(null);
            final List<Class<?>> filtered = classes.stream()
                    .filter(item -> interfaceCls.isAssignableFrom(item)
                            && item != interfaceCls)
                    .collect(Collectors.toList());
            final int size = filtered.size();
            // Non-Unique throw error out.
            Fn.flingUp(Values.ONE < size, LOGGER,
                    DuplicatedImplException.class,
                    Instance.class, interfaceCls);
            // Null means direct interface only.
            return Values.ONE == size ? filtered.get(Values.IDX) : null;
        }, interfaceCls);
    }

    private static <T> T construct(final Class<?> clazz,
                                   final Object... params) {
        return Fn.getJvm(() -> {
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
                ret = Fn.getJvm(() -> ((T) reference), reference);
            }
            return ret;
        }, clazz, params);
    }

    private static <T> T construct(final Class<T> clazz) {
        return Fn.getJvm(() -> {
            // Reflect Asm
            final ConstructorAccess<T> access = ConstructorAccess.get(clazz);
            return access.newInstance();
        }, clazz);
    }

    private Instance() {
    }
}
