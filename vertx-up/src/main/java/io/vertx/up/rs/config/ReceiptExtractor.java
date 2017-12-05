package io.vertx.up.rs.config;

import com.google.common.collect.Sets;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.Address;
import io.vertx.up.atom.Receipt;
import io.vertx.up.exception.AccessProxyException;
import io.vertx.up.exception.AddressWrongException;
import io.vertx.up.exception.NoArgConstructorException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.rs.Extractor;
import io.vertx.up.tool.StringUtil;
import io.vertx.up.tool.mirror.Anno;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Scanned @Queue clazz to build Receipt metadata
 */
public class ReceiptExtractor implements Extractor<Set<Receipt>> {

    private static final Annal LOGGER = Annal.get(ReceiptExtractor.class);

    private static final Set<String> ADDRESS = new ConcurrentHashSet<>();

    static {
        if (ADDRESS.isEmpty()) {
            /** 1. Get all endpoints **/
            final Set<Class<?>> endpoints = ZeroAnno.getEndpoints();

            /** 2. Scan for @Address to matching **/
            for (final Class<?> endpoint : endpoints) {
                // 3. Scan
                final Annotation[] annotations
                        = Anno.query(endpoint, Address.class);
                // 4. Extract address
                for (final Annotation addressAnno : annotations) {
                    final String address = Instance.invoke(addressAnno, "value");
                    if (!StringUtil.isNil(address)) {
                        ADDRESS.add(address);
                    }
                }
            }
        }
        /** 5.Log out address report **/
        LOGGER.info(Info.ADDRESS_IN, ADDRESS.size());
        ADDRESS.forEach(item -> {
            LOGGER.info(Info.ADDRESS_ITEM, item);
        });
    }

    @Override
    public Set<Receipt> extract(final Class<?> clazz) {
        return Fn.get(Sets.newConcurrentHashSet(), () -> {
            // 1. Class verify
            verify(clazz);
            // 2. Scan method to find @Address
            final Set<Receipt> receipts = new ConcurrentHashSet<>();
            final Method[] methods = clazz.getDeclaredMethods();
            for (final Method method : methods) {
                // 3. Only focus on annotated with @Address
                if (method.isAnnotationPresent(Address.class)) {
                    final Receipt receipt = extract(method);
                    if (null != receipt) {
                        receipts.add(receipt);
                    }
                }
            }
            return receipts;
        }, clazz);
    }

    private Receipt extract(final Method method) {
        // 1. Scan whole Endpoints
        final Class<?> clazz = method.getDeclaringClass();
        final Annotation annotation = method.getDeclaredAnnotation(Address.class);
        final String address = Instance.invoke(annotation, "value");

        // 2. Ensure address incoming.
        Fn.flingUp(!ADDRESS.contains(address), LOGGER,
                AddressWrongException.class,
                getClass(), address, clazz, method);

        final Receipt receipt = new Receipt();
        receipt.setMethod(method);
        receipt.setAddress(address);
        // Fix: Instance class for proxy
        final Object proxy = Instance.singleton(clazz);
        receipt.setProxy(proxy);
        return receipt;
    }

    private void verify(final Class<?> clazz) {
        // Check basic specification: No Arg Constructor
        Fn.flingUp(!Instance.noarg(clazz), LOGGER,
                NoArgConstructorException.class,
                getClass(), clazz);
        Fn.flingUp(!Modifier.isPublic(clazz.getModifiers()), LOGGER,
                AccessProxyException.class,
                getClass(), clazz);
    }
}
