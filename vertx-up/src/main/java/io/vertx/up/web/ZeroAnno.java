package io.vertx.up.web;

import io.vertx.core.impl.ConcurrentHashSet;
import io.vertx.up.annotations.Agent;
import io.vertx.up.annotations.EndPoint;
import io.vertx.up.annotations.Queue;
import io.vertx.up.annotations.Worker;
import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.Info;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.EventExtractor;
import io.vertx.up.rs.config.ReceiptExtractor;
import io.vertx.zero.func.HPool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Instance;
import io.vertx.zero.tool.mirror.Pack;

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
     * Class -> Queues
     */
    private final static Set<Receipt> RECEIPTS
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
     * Type -> Worker1, Worker2
     */
    private final static Set<Class<?>> WORKERS
            = new ConcurrentHashSet<>();

    /**
     * Get all agents.
     *
     * @return
     */
    public static ConcurrentMap<ServerType, List<Class<?>>> getAgents() {
        return AGENTS;
    }

    /**
     * Get all workers
     *
     * @return
     */
    public static Set<Class<?>> getWorkers() {
        return WORKERS;
    }

    /**
     * Get all receipts
     *
     * @return
     */
    public static Set<Receipt> getReceipts() {
        return RECEIPTS;
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

    /**
     * Provide to receipt extractor to validate valid address.
     *
     * @return
     */
    public static Set<Class<?>> getEndpoints() {
        return ENDPOINTS;
    }

    public static Set<Event> getEvents() {
        return EVENTS;
    }

    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        /** 2.Set to ENDPOINTS **/
        final Set<Class<?>> endpoints =
                clazzes.stream()
                        .filter((item) -> item.isAnnotationPresent(EndPoint.class))
                        .collect(Collectors.toSet());
        if (ENDPOINTS.isEmpty()) {
            ENDPOINTS.addAll(endpoints);
            LOGGER.info(Info.SCANED_ENDPOINT, endpoints.size());

            /** 2.1.Build Api metadata **/
            final Extractor<Set<Event>> extractor = Instance.singleton(EventExtractor.class);
            for (final Class<?> endpoint : ENDPOINTS) {
                final Set<Event> events = extractor.extract(endpoint);
                if (!events.isEmpty()) {

                    // 2.2. Report events for endpoint, wait for deployment.
                    LOGGER.info(Info.SCANED_EVENTS, endpoint.getName(), events.size());
                    EVENTS.addAll(events);
                }
            }
        }
        /** 3.Set to QUEUES **/
        final Set<Class<?>> queues =
                clazzes.stream()
                        .filter(item -> item.isAnnotationPresent(Queue.class))
                        .collect(Collectors.toSet());
        if (RECEIPTS.isEmpty()) {
            LOGGER.info(Info.SCANED_QUEUE, queues.size());
            /** 3.1. Build Metadata **/
            final Extractor<Set<Receipt>> extractor = Instance.singleton(ReceiptExtractor.class);
            for (final Class<?> queue : queues) {
                final Set<Receipt> receipts = extractor.extract(queue);
                if (!receipts.isEmpty()) {
                    // 2.2. Report receipts for endpoint, wait for deployment.
                    LOGGER.info(Info.SCANED_RECEIPTS, queue.getName(), receipts.size());
                    RECEIPTS.addAll(receipts);
                }
            }
        }
        /** 4.Set Agents **/
        final Set<Class<?>> agents =
                clazzes.stream()
                        .filter((item) -> item.isAnnotationPresent(Agent.class))
                        .collect(Collectors.toSet());
        /** 5.Scan duplicated **/
        if (AGENTS.isEmpty()) {
            AGENTS.putAll(
                    HPool.group(agents,
                            ZeroHelper::getAgentKey,
                            (item) -> item));
        }
        /** 6.Workers scanned **/
        final Set<Class<?>> workers =
                clazzes.stream().filter((item) -> item.isAnnotationPresent(Worker.class))
                        .collect(Collectors.toSet());
        if (WORKERS.isEmpty()) {
            WORKERS.addAll(workers);
        }
    }
}
