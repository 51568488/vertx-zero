package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.Info;
import io.vertx.up.exception.PluginSpecificationException;
import io.vertx.up.plugin.Infix;
import io.vertx.up.web.ZeroAmbient;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HNull;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class VertxPlugin {

    private static final Annal LOGGER = Annal.get(VertxPlugin.class);

    /**
     * Default package
     *
     * @param vertx
     */
    void connect(final Vertx vertx) {
        /** Scan all plugins **/
        final Set<String> plugins = ZeroAmbient.getPluginNames();

        for (final String plugin : plugins) {
            System.out.println(Thread.currentThread().getName());
            System.out.println(plugin);
            initialize(plugin, vertx);

            inject(plugin);
        }
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

    /**
     * Check whether clazz has the method of name
     *
     * @param clazz
     * @return
     */
    private Method findInit(final Class<?> clazz) {
        return HNull.get(() -> {
            final Method[] methods = clazz.getDeclaredMethods();
            Method found = null;
            for (final Method method : methods) {
                // Multi
                if ("init".equals(method.getName())) {
                    // 1. Method must be public static void
                    if (validMethod(method)) {
                        found = method;
                        break;
                    }
                }
            }
            return found;
        }, clazz);
    }

    private boolean validMethod(final Method method) {
        return (void.class == method.getReturnType() || Void.class == method.getReturnType())
                && Modifier.isStatic(method.getModifiers())
                && Modifier.isPublic(method.getModifiers());
    }
}
