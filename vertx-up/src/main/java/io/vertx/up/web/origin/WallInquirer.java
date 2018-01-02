package io.vertx.up.web.origin;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Wall;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.exception.DuplicatedWallException;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Codec;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @Wall
 */
public class WallInquirer implements Inquirer<Set<Cliff>> {

    private static final Annal LOGGER = Annal.get(WallInquirer.class);

    private static final Node<JsonObject> NODE = Instance.singleton(ZeroUniform.class);

    @Override
    public Set<Cliff> scan(final Set<Class<?>> walls) {
        /** 1. Build result **/
        final Set<Cliff> wallSet = new TreeSet<>();
        final Set<Class<?>> wallClses = walls.stream()
                .filter((item) -> item.isAnnotationPresent(Wall.class))
                .collect(Collectors.toSet());
        /** 2. Duplicated checking for wallClses **/
        this.verify(wallClses);
        /** 3. Transfer **/
        return wallSet;
    }

    private void verify(final Set<Class<?>> wallClses) {
        /** Wall duplicated **/
        final Set<String> hashs = new HashSet<>();
        final Set<String> keys = new HashSet<>();
        Observable.fromIterable(wallClses)
                .filter(Objects::nonNull)
                .map(item -> item.getAnnotation(Wall.class))
                .filter(Objects::nonNull)
                .map(item -> {
                    // Add configuration key into keys;
                    keys.add(Instance.invoke(item, "value"));
                    return this.hashPath(item);
                }).subscribe(hashs::add);
        Fn.flingUp(hashs.size() != wallClses.size(), LOGGER,
                DuplicatedWallException.class, getClass(),
                wallClses.stream().map(Class::getName).collect(Collectors.toSet()));
        /** Shared key does not existing **/
        final JsonObject config = NODE.read();
    }

    /**
     * Path or Order must be not the same or duplicated.
     *
     * @param annotation
     * @return
     */
    private String hashPath(final Annotation annotation) {
        final Integer order = Instance.invoke(annotation, "order");
        final String path = Instance.invoke(annotation, "path");
        return Codec.sha256(order + path);
    }
}
