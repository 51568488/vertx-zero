package io.vertx.up.web.origin;

import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.up.annotations.Wall;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.secure.cliff.WallTransformer;
import io.vertx.up.tool.Codec;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.zero.exception.DynamicKeyMissingException;
import io.vertx.zero.exception.WallDuplicatedException;
import io.vertx.zero.exception.WallKeyMissingException;
import io.vertx.zero.marshal.Transformer;
import io.vertx.zero.marshal.node.Node;
import io.vertx.zero.marshal.node.ZeroUniform;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @Wall
 */
public class WallInquirer implements Inquirer<Set<Cliff>> {

    private static final Annal LOGGER = Annal.get(WallInquirer.class);

    private static final Node<JsonObject> NODE =
            Instance.singleton(ZeroUniform.class);

    private static final String KEY = "secure";

    private transient final Transformer<Cliff> transformer =
            Instance.singleton(WallTransformer.class);

    @Override
    public Set<Cliff> scan(final Set<Class<?>> walls) {
        /** 1. Build result **/
        final Set<Cliff> wallSet = new TreeSet<>();
        final Set<Class<?>> wallClses = walls.stream()
                .filter((item) -> item.isAnnotationPresent(Wall.class))
                .collect(Collectors.toSet());
        if (!wallClses.isEmpty()) {
            // It means that you have set Wall and enable security configuration
            // wallClses verification
            final JsonObject config = this.verify(wallClses);
            for (final String field : config.fieldNames()) {
                // Difference key setting
                wallSet.add(this.transformer.transform(config.getJsonObject(field)));
            }
        }
        /** 3. Transfer **/
        return wallSet;
    }

    private JsonObject verify(final Set<Class<?>> wallClses) {
        /** Wall duplicated **/
        final Set<String> hashs = new HashSet<>();
        final ConcurrentMap<String, Class<?>> keys = new ConcurrentHashMap<>();
        Observable.fromIterable(wallClses)
                .filter(Objects::nonNull)
                .map(item -> {
                    final Annotation annotation = item.getAnnotation(Wall.class);
                    // Add configuration key into keys;
                    keys.put(Instance.invoke(annotation, "value"), item);
                    return this.hashPath(annotation);
                }).subscribe(hashs::add);
        // Duplicated adding.
        Fn.flingUp(hashs.size() != wallClses.size(), LOGGER,
                WallDuplicatedException.class, getClass(),
                wallClses.stream().map(Class::getName).collect(Collectors.toSet()));
        /** Shared key does not existing **/
        final JsonObject config = NODE.read();
        Fn.flingUp(!config.containsKey(KEY), LOGGER,
                DynamicKeyMissingException.class, getClass(),
                KEY, config);
        /** Wall key missing **/
        final JsonObject hitted = config.getJsonObject(KEY);
        for (final String key : keys.keySet()) {
            Fn.flingUp(null == hitted || !hitted.containsKey(key), LOGGER,
                    WallKeyMissingException.class, getClass(),
                    key, keys.get(key));
        }
        return config.getJsonObject(KEY);
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
