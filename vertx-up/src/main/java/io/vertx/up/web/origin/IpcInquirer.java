package io.vertx.up.web.origin;

import io.reactivex.Observable;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.tool.mirror.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Ipc and @Address must be in @Queue class instead of other specification.
 */
public class IpcInquirer implements Inquirer<ConcurrentMap<String, Method>> {

    /**
     * @param classes all classes must be annotated with @Queue
     * @return
     */
    @Override
    public ConcurrentMap<String, Method> scan(final Set<Class<?>> classes) {
        /**
         * Here are some specification for IPC community
         * 1. As IPC server, must extract @Ipc ( from ) part and registred to Etcd
         * 2. This address is published and other nodes could visit current Micro service.
         * 3. Scan all classes annotated with @Queue only, @Ipc must be used inner @Queue
         */
        final ConcurrentMap<String, Method> addresses = new ConcurrentHashMap<>();
        Observable.fromIterable(classes)
                .flatMap(clazz -> Observable.fromArray(clazz.getDeclaredMethods()))
                .filter(Objects::nonNull)
                .filter(method -> method.isAnnotationPresent(Ipc.class))
                .subscribe(method -> {
                    final Annotation annotation = method.getAnnotation(Ipc.class);
                    final String address = Instance.invoke(annotation, "value");
                    addresses.put(address, method);
                });
        return addresses;
    }
}
