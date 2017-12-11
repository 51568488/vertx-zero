package io.vertx.up.web.anima;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.Plugins;
import io.vertx.up.exception.InjectionLimeKeyException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.plugin.Infix;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.tool.mirror.Anno;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAmbient;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * Injection system
 */
public class AffluxScatter implements Scatter {

    private static final Annal LOGGER = Annal.get(AffluxScatter.class);

    private static final ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>
            PENDINGS = ZeroAnno.getPlugins();

    @Override
    public void connect(final Vertx vertx) {
        // Extract all events.
        final Set<Event> events = ZeroAnno.getEvents();
        Fn.itSet(events, (item, index) -> {
            Runner.run(() -> {
                inject(item.getProxy());
            }, "event-afflux-" + index);
        });
        // Extract all receipts.
        final Set<Receipt> receipts = ZeroAnno.getReceipts();
        Fn.itSet(receipts, (item, index) -> {
            Runner.run(() -> {
                inject(item.getProxy());
            }, "receipt-afflux-" + index);
        });
    }

    private void inject(final Object proxy) {
        Fn.safeNull(() -> {
            final Class<?> clazz = proxy.getClass();
            if (PENDINGS.containsKey(clazz)) {
                // Scanned in started up
                final ConcurrentMap<String, Class<?>> injections
                        = PENDINGS.get(clazz);
                // Inject field
                injections.forEach((key, type) -> {
                    // Field set
                    try {
                        final Field field = clazz.getDeclaredField(key);
                        final Object instance;
                        if (Anno.isMark(field, Plugins.INFIX_MAP.keySet())) {
                            // Speicific Annotation
                            instance = inject(field);
                        } else {
                            // Inject Only
                            instance = Instance.singleton(type);
                        }
                        // Set for field
                        if (null != instance) {
                            Instance.set(proxy, key, instance);
                            // Scan continue for field
                            inject(instance);
                        }
                    } catch (final NoSuchFieldException ex) {
                        LOGGER.jvm(ex);
                    }
                });
            }
        }, proxy);
    }

    private Class<? extends Annotation> search(
            final Field field
    ) {
        final Annotation[] annotations = field.getDeclaredAnnotations();
        final Set<Class<? extends Annotation>>
                annotationCls = Plugins.INFIX_MAP.keySet();
        Class<? extends Annotation> hitted = null;
        for (final Annotation annotation : annotations) {
            if (annotationCls.contains(annotation.annotationType())) {
                hitted = annotation.annotationType();
                break;
            }
        }
        return hitted;
    }

    private Object inject(final Field field) {
        final Class<? extends Annotation> key = search(field);
        final String pluginKey = Plugins.INFIX_MAP.get(key);
        final Class<?> infixCls = ZeroAmbient.getPlugin(pluginKey);
        Object ret = null;
        if (null != infixCls) {
            if (Instance.isMatch(infixCls, Infix.class)) {
                // Config checking
                final Node<JsonObject> node = Instance.instance(ZeroUniform.class);
                final JsonObject options = node.read();
                Fn.flingUp(!options.containsKey(pluginKey), LOGGER,
                        InjectionLimeKeyException.class,
                        getClass(), infixCls, pluginKey);

                final Infix reference = Instance.singleton(infixCls);
                ret = Instance.invoke(reference, "get");
            } else {
                LOGGER.warn(Info.INFIX_IMPL, infixCls.getName(), Infix.class.getName());
            }
        } else {
            LOGGER.warn(Info.INFIX_NULL, pluginKey, field.getName(), field.getDeclaringClass().getName());
        }
        return ret;
    }
}
