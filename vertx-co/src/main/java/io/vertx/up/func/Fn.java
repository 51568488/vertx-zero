package io.vertx.up.func;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.up.log.Annal;
import io.vertx.zero.exception.ZeroException;
import io.vertx.zero.exception.ZeroRunException;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Unique interface to call function
 */
public class Fn {
    /**
     * ZeroException out.
     *
     * @param logger
     * @param zeroClass
     * @param args
     * @throws ZeroException
     */
    public static void flingZero(
            final boolean condition,
            final Annal logger,
            final Class<? extends ZeroException> zeroClass,
            final Object... args
    ) throws ZeroException {
        if (condition) {
            Announce.outZero(logger, zeroClass, args);
        }
    }

    /**
     * ZeroRunException out.
     *
     * @param condition
     * @param logger
     * @param upClass
     * @param args
     */
    public static void flingUp(
            final boolean condition,
            final Annal logger,
            final Class<? extends ZeroRunException> upClass,
            final Object... args
    ) {
        if (condition) {
            Announce.outUp(logger, upClass, args);
        }
    }

    /**
     * Defined jvm function to simply Exception function
     *
     * @param actuator
     * @param logger
     */
    public static void safeJvm(
            final JvmActuator actuator,
            final Annal logger
    ) {
        Defend.jvmVoid(actuator, logger);
    }

    /**
     * @param supplier
     * @param logger
     * @param <T>
     * @return
     */
    public static <T> T safeJvm(
            final JvmSupplier<T> supplier,
            final Annal logger
    ) {
        return Defend.jvmReturn(supplier, logger);
    }

    /**
     * @param supplier
     * @param logger
     * @param <T>
     * @return
     */
    public static <T> T safeZero(
            final ZeroSupplier<T> supplier,
            final Annal logger
    ) {
        return Defend.zeroReturn(supplier, logger);
    }

    /**
     * @param actuator
     * @param logger
     */
    public static void safeZero(
            final ZeroActuator actuator,
            final Annal logger
    ) {
        Defend.zeroVoid(actuator, logger);
    }

    /**
     * @param actuator
     */
    public static void safeNull(
            final Actuator actuator,
            final Object... input
    ) {
        Zero.exec(actuator, input);
    }

    /**
     * @param consumer
     * @param input
     * @param <T>
     */
    public static <T> void safeNull(
            final Consumer<T> consumer,
            final T input
    ) {
        Zero.exec(consumer, input);
    }


    public static void safeSemi(
            final boolean condition,
            final Annal logger,
            final Actuator tSupplier,
            final Actuator fSupplier
    ) {
        Semi.exec(condition, logger, tSupplier, fSupplier);
    }

    /**
     * @param condition
     * @param logger
     * @param tSupplier
     */
    public static void safeSemi(
            final boolean condition,
            final Annal logger,
            final Actuator tSupplier
    ) {
        Semi.exec(condition, logger, tSupplier, null);
    }

    /**
     * @param supplier
     * @param runCls
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T transRun(
            final Supplier<T> supplier,
            final Class<? extends ZeroRunException> runCls,
            final Object... args
    ) {
        return Deliver.execRun(supplier, runCls, args);
    }

    /**
     * @param actuator
     * @param logger
     */
    public static void shuntRun(
            final Actuator actuator,
            final Annal logger
    ) {
        Announce.shuntRun(actuator, logger);
    }

    /**
     * @param actuator
     * @param input
     */
    public static void shuntZero(
            final ZeroActuator actuator,
            final Object... input
    ) throws ZeroException {
        Zero.execZero(actuator, input);
    }

    /**
     * @param supplier
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T getJvm(
            final JvmSupplier<T> supplier,
            final Object... input
    ) {
        return Zero.getJvm(null, supplier, input);
    }

    /**
     * @param supplier
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T get(
            final Supplier<T> supplier,
            final Object... input
    ) {
        return Zero.get(null, supplier, input);
    }

    /**
     * @param condition
     * @param logger
     * @param tSupplier
     * @param fSupplier
     * @param <T>
     * @return
     */
    public static <T> T getSemi(
            final boolean condition,
            final Annal logger,
            final Supplier<T> tSupplier,
            final Supplier<T> fSupplier
    ) {
        return Defend.zeroReturn(() -> Semi.execZero(condition,
                tSupplier::get, fSupplier::get), logger);
    }

    public static <T> T getSemi(
            final boolean condition,
            final Annal logger,
            final Supplier<T> tSupplier
    ) {
        return Defend.zeroReturn(() -> Semi.execZero(condition,
                tSupplier::get, null), logger);
    }

    /**
     * @param condition
     * @param tSupplier
     * @param fSupplier
     * @param <T>
     * @return
     * @throws ZeroException
     */
    public static <T> T getSemi(
            final boolean condition,
            final ZeroSupplier<T> tSupplier,
            final ZeroSupplier<T> fSupplier
    ) throws ZeroException {
        return Semi.execZero(condition, tSupplier, fSupplier);
    }

    /**
     * @param defaultValue
     * @param supplier
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T get(
            final T defaultValue,
            final Supplier<T> supplier,
            final Object... input
    ) {
        return Zero.get(defaultValue, supplier, input);
    }

    /**
     * @param defaultValue
     * @param supplier
     * @param input
     * @param <T>
     * @return
     */
    public static <T> T getJvm(
            final T defaultValue,
            final JvmSupplier<T> supplier,
            final Object... input
    ) {
        return Zero.getJvm(defaultValue, supplier, input);
    }

    /**
     * @param map
     * @param fnEach
     * @param <K>
     * @param <V>
     */
    public static <K, V> void itMap(
            final ConcurrentMap<K, V> map,
            final BiConsumer<K, V> fnEach) {
        Congregation.exec(map, fnEach);
    }

    /**
     * @param set
     * @param fnEach
     * @param <V>
     */
    public static <V> void itSet(
            final Set<V> set,
            final BiConsumer<V, Integer> fnEach
    ) {
        final List<V> list = new ArrayList<>(set);
        Congregation.exec(list, fnEach);
    }

    /**
     * @param list
     * @param fnEach
     * @param <V>
     */
    public static <V> void itList(
            final List<V> list,
            final BiConsumer<V, Integer> fnEach
    ) {
        Congregation.exec(list, fnEach);
    }

    /**
     * @param array
     * @param fnEach
     * @param <V>
     */
    public static <V> void itArray(
            final V[] array,
            final BiConsumer<V, Integer> fnEach
    ) {
        Congregation.exec(Arrays.asList(array), fnEach);
    }

    /**
     * @param array
     * @param fnEach
     * @param <V>
     */
    public static <V> void itMatrix(
            final V[][] array,
            final Consumer<V> fnEach
    ) {
        Congregation.exec(array, fnEach);
    }

    /**
     * @param data
     * @param fnEach
     * @param <T>
     */
    public static <T> void itJObject(
            final JsonObject data,
            final BiConsumer<T, String> fnEach
    ) {
        Congregation.exec(data, fnEach);
    }

    /**
     * @param data
     * @param fnIt
     * @param <T>
     * @throws ZeroException
     */
    public static <T> void etJObject(
            final JsonObject data,
            final ZeroBiConsumer<T, String> fnIt
    ) throws ZeroException {
        Congregation.execZero(data, fnIt);
    }

    /**
     * @param array
     * @param clazz
     * @param fnEach
     * @param <T>
     */
    public static <T> void itJArray(
            final JsonArray array,
            final Class<T> clazz,
            final BiConsumer<T, Integer> fnEach
    ) {
        Congregation.exec(array, clazz, fnEach);
    }

    /**
     * @param dataArray
     * @param clazz
     * @param fnIt
     * @param <T>
     * @throws ZeroException
     */
    public static <T> void etJArray(
            final JsonArray dataArray,
            final Class<T> clazz,
            final ZeroBiConsumer<T, Integer> fnIt
    ) throws ZeroException {
        Congregation.execZero(dataArray, clazz, fnIt);
    }

    /**
     * @param dataArray
     * @param fnIt
     * @param <T>
     */
    public static <T> void etJArray(
            final JsonArray dataArray,
            final ZeroBiConsumer<T, String> fnIt
    ) throws ZeroException {
        Congregation.execZero(dataArray, fnIt);
    }

    /**
     * Pool
     *
     * @param pool
     * @param key
     * @param poolFn
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> V pool(
            final ConcurrentMap<K, V> pool,
            final K key,
            final Supplier<V> poolFn) {
        return Pond.exec(pool, key, poolFn);
    }

    /**
     * Thread pool
     *
     * @param pool
     * @param poolFn
     * @param <V>
     * @return
     */
    public static <V> V poolThread(
            final ConcurrentMap<String, V> pool,
            final Supplier<V> poolFn) {
        final String threadName = Thread.currentThread().getName();
        return Pond.exec(pool, threadName, poolFn);
    }

    /**
     * @param object
     * @param keyFn
     * @param valueFn
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    public static <K, V, E> ConcurrentMap<K, List<V>> packet(
            final Collection<E> object,
            final Function<E, K> keyFn,
            final Function<E, V> valueFn
    ) {
        return Pond.group(object, keyFn, valueFn);
    }

    /**
     * @param object
     * @param keyFn
     * @param valueFn
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    public static <K, V, E> ConcurrentMap<K, V> zipper(
            final E[] object,
            final Function<E, K> keyFn,
            final Function<E, V> valueFn) {
        return Pond.zipper(Arrays.asList(object), keyFn, valueFn);
    }

    /**
     * @param object
     * @param keyFn
     * @param valueFn
     * @param <K>
     * @param <V>
     * @param <E>
     * @return
     */
    public static <K, V, E> ConcurrentMap<K, V> zipper(
            final Collection<E> object,
            final Function<E, K> keyFn,
            final Function<E, V> valueFn
    ) {
        return Pond.zipper(object, keyFn, valueFn);
    }

    /**
     * @param reference
     * @param tranFn
     * @param supplier
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> T nullFlow(final F reference,
                                    final Function<F, T> tranFn,
                                    final Supplier<T> supplier) {
        return Zero.nullFlow(reference, tranFn, supplier);
    }

    /**
     * Spec function
     *
     * @return
     */
    public static <T> T nil() {
        return null;
    }
}
