package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.exception.up.PluginSpecificationException;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.ce.Event;
import io.vertx.up.ce.Receipt;
import io.vertx.up.cv.Info;
import io.vertx.zero.core.plugin.Infix;
import io.vertx.zero.web.ZeroAmbient;
import io.vertx.zero.web.ZeroAnno;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.fun.HTry;
import org.vie.util.Instance;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VertxPlugin {

    private static final Annal LOGGER = Annal.get(VertxPlugin.class);

    private static final ConcurrentMap<String, Infix> INFIXES
            = new ConcurrentHashMap<>();

    /**
     * Default package
     *
     * @param vertx
     */
    void connect(final Vertx vertx) {
        /** Scan all plugins **/
        final Set<String> plugins = ZeroAmbient.getPluginNames();
        for (final String plugin : plugins) {

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
