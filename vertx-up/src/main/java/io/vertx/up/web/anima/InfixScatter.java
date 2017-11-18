package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.up.annotations.Plugin;
import io.vertx.up.eon.Plugins;
import io.vertx.up.exception.PluginSpecificationException;
import io.vertx.up.web.ZeroAmbient;
import io.vertx.zero.eon.Values;
import io.vertx.zero.func.HBool;
import io.vertx.zero.func.HNull;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.marshal.Node;
import io.vertx.zero.marshal.node.ZeroLime;
import io.vertx.zero.tool.Statute;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Infix initialization
 */
public class InfixScatter implements Scatter {

    private static final Annal LOGGER = Annal.get(InfixScatter.class);

    private static final Node<ConcurrentMap<String, String>> node =
            Instance.singleton(ZeroLime.class);

    @Override
    public void connect(final Vertx vertx) {
        /** Enabled **/
        final ConcurrentMap<String, Class<?>> enabled =
                Statute.reduce(node.read().keySet(), ZeroAmbient.getInjections());
        /** Scan all Infix **/
        final ConcurrentMap<Class<? extends Annotation>, Class<?>> injections =
                Statute.reduce(Plugins.INFIX_MAP, enabled);

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
