package io.vertx.up;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.exception.up.PluginSpecificationException;
import io.vertx.up.annotations.Plugin;
import io.vertx.zero.core.ZeroNode;
import io.vertx.zero.core.config.ZeroPlugin;
import io.vertx.zero.cv.Plugins;
import io.vertx.zero.web.ZeroAmbient;
import org.vie.fun.HBool;
import org.vie.fun.HNull;
import org.vie.fun.HTry;
import org.vie.util.Instance;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class VertxPlugin {

    private static final Annal LOGGER = Annal.get(VertxPlugin.class);

    private static final ZeroNode<JsonObject> INJECTS
            = Instance.singleton(ZeroPlugin.class, Plugins.INJECT);

    private static final JsonObject DATA = INJECTS.read();

    /**
     * Default package
     *
     * @param vertx
     */
    void connect(final Vertx vertx) {
        /** 1. Plugin for vertx **/
        final Set<String> plugins = ZeroAmbient.getPluginNames();
        for (final String plugin : plugins) {
            // 2. Initialize
            final Class<?> clazz = ZeroAmbient.getPlugin(plugin);
            if (Anno.isMark(clazz, Plugin.class)) {
                // 3. Get the init static method to call
                final Method method = findInit(clazz);
                // 4. Specification checking.
                HBool.execUp(null == method, LOGGER,
                        PluginSpecificationException.class,
                        getClass(), plugin);
                HTry.execJvm(() -> {
                    method.invoke(null, vertx);
                    return null;
                }, LOGGER);
            }
        }
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
