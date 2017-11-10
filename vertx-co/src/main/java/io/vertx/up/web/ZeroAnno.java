package io.vertx.up.web;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.Agent;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.ce.Event;
import io.vertx.up.cv.Message;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.EndPointExtractor;
import org.vie.cv.em.ServerType;
import org.vie.fun.HPool;
import org.vie.util.Instance;
import org.vie.util.log.Annal;
import org.vie.util.mirror.Anno;
import org.vie.util.mirror.Pack;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Transfer Class<?> set to difference mapping.
 */
public class ZeroAnno {

    private static final Annal LOGGER = Annal.get(ZeroAnno.class);
    /**
     * Class -> EndPoint
     */
    private final static Set<Class<?>> ENDPOINTS
            = new ConcurrentHashSet<>();
    /**
     * Event Set
     */
    private final static Set<Event> EVENTS
            = new ConcurrentHashSet<>();
    /**
     * Class -> PATH
     */
    private final static ConcurrentMap<ServerType, List<Class<?>>> AGENTS
            = new ConcurrentHashMap<>();

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
        return ZeroHelper.isAgentDefined(input, excludes);
    }

    public static Set<Event> getEvents() {
        return EVENTS;
    }

    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        /** 2.Set to ENDPOINTS **/
        final Set<Class<?>> routines =
                clazzes.stream()
                        .filter((item) -> Anno.isMark(item, EndPoint.class))
                        .collect(Collectors.toSet());
        if (ENDPOINTS.isEmpty()) {
            ENDPOINTS.addAll(routines);
            LOGGER.info(Message.SCANED_ENDPOINT, routines.size());
            /** 3.Build Api metadata **/
            final Extractor<Set<Event>> extractor = Instance.singleton(EndPointExtractor.class);
            for (final Class<?> endpoint : ENDPOINTS) {
                final Set<Event> events = extractor.extract(endpoint);
                if (!events.isEmpty()) {
                    // 4. Report events for endpoint, wait for deployment.
                    LOGGER.info(Message.SCANED_EVENTS, endpoint.getName(), events.size());
                    EVENTS.addAll(events);
                }
            }
        }
        /** 4.Set Agents **/
        final Set<Class<?>> agents =
                clazzes.stream()
                        .filter((item) -> Anno.isMark(item, Agent.class))
                        .collect(Collectors.toSet());
        /** 5.Scan duplicated **/
        if (AGENTS.isEmpty()) {
            AGENTS.putAll(
                    HPool.group(agents,
                            ZeroHelper::getAgentKey,
                            (item) -> item));
        }
    }
}
