package io.vertx.up.rs;

import com.vie.cv.em.ServerType;
import com.vie.fun.HPool;
import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.Agent;
import io.vertx.up.annotations.Routine;
import io.vertx.up.mirror.Anno;
import io.vertx.up.mirror.Pack;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Transfer Class<?> set to difference mapping.
 */
public class VertxAnno {
    /**
     * Class -> Routine
     */
    private final static Set<Class<?>> ROUTINES
            = new ConcurrentHashSet<>();
    /**
     * Class -> PATH
     */
    private final static ConcurrentMap<ServerType, List<Class<?>>> AGENTS
            = new ConcurrentHashMap<>();

    /**
     * Get all routines
     *
     * @return
     */
    public static Set<Class<?>> getRoutines() {
        return ROUTINES;
    }

    /**
     * Get all agents.
     *
     * @return
     */
    public static ConcurrentMap<ServerType, List<Class<?>>> getAgents() {
        return AGENTS;
    }

    /**
     * Checked defined.
     *
     * @param input
     * @param excludes
     * @return
     */
    public static ConcurrentMap<ServerType, Boolean> isDefined(
            final ConcurrentMap<ServerType, List<Class<?>>> input,
            final Class<?>... excludes
    ) {
        return AnnoHelper.isAgentDefined(input, excludes);
    }

    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        /** 2.Set to ROUTINES **/
        final Set<Class<?>> routines =
                clazzes.stream()
                        .filter((item) -> Anno.isMark(item, Routine.class))
                        .collect(Collectors.toSet());
        ROUTINES.addAll(routines);
        /** 3.Set Agents **/
        final Set<Class<?>> agents =
                clazzes.stream()
                        .filter((item) -> Anno.isMark(item, Agent.class))
                        .collect(Collectors.toSet());
        /** 4.Scan duplicated **/
        if (AGENTS.isEmpty()) {
            AGENTS.putAll(
                    HPool.group(agents,
                            AnnoHelper::getAgentKey,
                            (item) -> item));
        }
    }
}
