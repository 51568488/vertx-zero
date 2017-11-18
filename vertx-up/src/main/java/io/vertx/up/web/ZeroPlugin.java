package io.vertx.up.web;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.Plugins;
import io.vertx.up.exception.PluginSpecificationException;
import io.vertx.up.plugin.Infix;
import io.vertx.zero.eon.Values;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HNull;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.Statute;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ZeroPlugin {

    private static final Annal LOGGER = Annal.get(ZeroPlugin.class);

    private transient final ZeroAfflux injector = Instance.singleton(ZeroAfflux.class);

    /**
     * Default package
     *
     * @param vertx
     */
    public void connect(final Vertx vertx) {
        // Infix initialized
        initInfix(vertx);
        // Injection system started up
    }


    private void initialize(final String plugin, final Vertx vertx) {
        // Initialize
        final Class<?> clazz = ZeroAmbient.getPlugin(plugin);
        if (null != clazz && clazz.isAnnotationPresent(Plugin.class)) {
            final Method method = findInit(clazz);
            // Specification checking.
            HBool.execUp(null == method, LOGGER,
                    PluginSpecificationException.class,
                    getClass(), plugin);
            HTry.execJvm(() -> {
                method.invoke(null, vertx);
                return null;
            }, LOGGER);
        }
    }

    private void inject(final String plugin) {
        // Extract all events.
        final Set<Event> events = ZeroAnno.getEvents();
        for (final Event event : events) {
            inject(event.getProxy());
        }
        // Extract all receipts.
        final Set<Receipt> receipts = ZeroAnno.getReceipts();
        for (final Receipt receipt : receipts) {
            inject(receipt.getProxy());
        }
    }

    private void inject(final Object proxy) {
        HNull.exec(() -> {
            final Class<?> clazz = proxy.getClass();
            final Field[] fields = clazz.getDeclaredFields();
            for (final Field field : fields) {
                final String pluginKey = Anno.getPlugin(field);
                if (null != pluginKey) {
                    final Class<?> infixCls = ZeroAmbient.getPlugin(pluginKey);
                    // If infix
                    if (null != infixCls) {
                        final List<Class<?>> infixes = Arrays.asList(infixCls.getInterfaces());
                        if (infixes.contains(Infix.class)) {
                            final Object seted = Instance.get(proxy, field.getName());
                            if (null == seted) {
                                // Infix
                                final Infix reference = Instance.singleton(infixCls);
                                final Object invoked = Instance.invoke(reference, "get");
                                Instance.set(proxy, field.getName(), invoked);
                                LOGGER.info(Info.INFIX_INJECT, infixCls.getName(),
                                        clazz.getName(), field.getName());
                            }
                        }
                    }
                }
            }
        }, proxy);
    }

    private void initInfix(final Vertx vertx) {
        /** Scan all plugins **/
        final ConcurrentMap<Class<? extends Annotation>, Class<?>>
                injections = Statute.zipperMerge(Plugins.INFIX_MAP, ZeroAmbient.getInjections());
        injections.values().stream().forEach(item -> {
            if (null != item && item.isAnnotationPresent(Plugin.class)) {
                final Method method = findInit(item);
                HBool.execUp(null == method, LOGGER,
                        PluginSpecificationException.class,
                        getClass(), item.getName());
                HTry.execJvm(() -> {
                    method.invoke(null, vertx);
                    return null;
                }, LOGGER);
            }
        });
    }

    /**
     * Check whether clazz has the method of name
     *
     * @param clazz
     * @return
     */
    private Method findInit(final Class<?> clazz) {
        return HNull.get(() -> {
            final Method[] methods = clazz.getDeclaredMethods();
            final List<Method> found = Arrays.stream(methods)
                    .filter(item -> "init".equals(item.getName()) && validMethod(item))
                    .collect(Collectors.toList());
            return Values.ONE == found.size() ? found.get(Values.IDX) : null;
        }, clazz);
    }

    private boolean validMethod(final Method method) {
        return (void.class == method.getReturnType() || Void.class == method.getReturnType())
                && Modifier.isStatic(method.getModifiers())
                && Modifier.isPublic(method.getModifiers());
    }
}
