package io.vertx.up.web.origin;

import io.vertx.up.annotations.Wall;
import io.vertx.up.atom.secure.Cliff;
import io.vertx.up.log.Annal;

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
        System.out.println(wallClses);
    }
}
