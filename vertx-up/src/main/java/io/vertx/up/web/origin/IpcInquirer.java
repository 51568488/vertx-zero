package io.vertx.up.web.origin;

import io.reactivex.Observable;
import io.vertx.up.annotations.Ipc;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.eon.Strings;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Ipc and @Address must be in @Queue class instead of other specification.
 */
public class IpcInquirer implements Inquirer<Set<String>> {
    private static final Annal LOGGER = Annal.get(IpcInquirer.class);

    /**
     * @param classes all classes must be annotated with @Queue
     * @return
     */
    @Override
    public Set<String> scan(final Set<Class<?>> classes) {
        // 1. Scan all classes for @Queue
        final Set<String> addresses = new TreeSet<>();
        Observable.fromIterable(classes)
                .flatMap(clazz -> Observable.fromArray(clazz.getDeclaredMethods()))
                .filter(Objects::nonNull)
                .filter(method -> method.isAnnotationPresent(Ipc.class))
                .map(method -> method.getAnnotation(Ipc.class))
                .filter(this::isValid)
                .map(annotation -> Instance.invoke(annotation, "value"))
                .filter(Objects::nonNull)
                .map(Object::toString)
                .subscribe(addresses::add);
        return addresses;
    }

    private boolean isValid(final Annotation annotation) {
        final String name = Instance.invoke(annotation, "name");
        return null == name || Strings.EMPTY.equals(name.trim());
    }
}
