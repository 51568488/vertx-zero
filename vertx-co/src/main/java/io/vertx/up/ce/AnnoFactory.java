package io.vertx.up.ce;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.Routine;
import io.vertx.up.mirror.Anno;
import io.vertx.up.mirror.Pack;

import javax.ws.rs.Path;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Transfer Class<?> set to difference mapping.
 */
public class AnnoFactory {
    /**
     * Class -> Routine
     */
    private final static Set<Class<?>> ROUTINES
            = new ConcurrentHashSet<>();
    /**
     * Class -> PATH
     */
    private final static Set<Class<?>> PATHES
            = new ConcurrentHashSet<>();
    /**
     * Class -> Meta Action
     */
    public final static Set<MetaAction> ACTIONS
            = new ConcurrentHashSet<>();

    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        /** 2.Set to ROUTINES **/
        final Set<Class<?>> routines =
                clazzes.stream()
                        .filter((item) -> Anno.isMark(item, Routine.class))
                        .collect(Collectors.toSet());
        ROUTINES.addAll(routines);
        System.out.println(routines.size());
        /** 3.Set to PATH **/
        final Set<Class<?>> pathes =
                clazzes.stream()
                        .filter((item) -> Anno.isMark(item, Path.class))
                        .collect(Collectors.toSet());
        PATHES.addAll(pathes);
        /** 4.Build Meta Action **/
        if (!ROUTINES.isEmpty()) {

        }
    }

    public static Set<Class<?>> getRoutines() {
        return ROUTINES;
    }
}
