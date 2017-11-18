package io.vertx.up.web.anima;

import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.Info;
import io.vertx.up.plugin.Infix;
import io.vertx.up.web.ZeroAmbient;
import io.vertx.up.web.ZeroAnno;
import io.vertx.zero.func.HNull;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Anno;
import io.vertx.zero.tool.mirror.Instance;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ZeroAfflux {

    private static final Annal LOGGER = Annal.get(ZeroAfflux.class);

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
}

