package io.vertx.up.web.origin;

import io.reactivex.Observable;
import io.vertx.up.annotations.Wall;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.log.Annal;
import io.vertx.up.tool.Codec;
import io.vertx.up.tool.mirror.Instance;

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

    @Override
    public Set<Cliff> scan(final Set<Class<?>> walls) {
        /** 1. Build result **/
        final Set<Cliff> wallSet = new TreeSet<>();
        final Set<Class<?>> wallClses = walls.stream()
                .filter((item) -> item.isAnnotationPresent(Wall.class))
                .collect(Collectors.toSet());
        /** 2. Duplicated checking for wallClses **/
        this.verify(wallClses);
        return wallSet;
    }

    private void verify(final Set<Class<?>> wallClses) {
        /** 3. Set hash key **/
        final Set<String> hashs = new HashSet<>();
        Observable.fromIterable(wallClses)
                .filter(Objects::nonNull)
                .map(item -> item.getAnnotation(Wall.class))
                .filter(Objects::nonNull)
                .map(annotation -> {
                    final String key = Instance.invoke(annotation, "value");
                    final Integer order = Instance.invoke(annotation, "order");
                    return Codec.sha256(key + order);
                }).subscribe(hashs::add);
        System.out.println(hashs.size());
        System.out.println(wallClses.size());
    }
}
