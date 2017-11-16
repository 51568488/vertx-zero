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
import io.vertx.up.web.thread.EndPointThread;
import io.vertx.up.web.thread.QueueThread;
import io.vertx.zero.func.HPool;
import io.vertx.zero.func.HTry;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.mirror.Pack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
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
     * Class -> Inject
     */
    private final static ConcurrentMap<Class<?>, ConcurrentMap<String, Field>> PENDINGS
            = new ConcurrentHashMap<>();
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
     * Get all plugins
     *
     * @return
     */
    public static ConcurrentMap<Class<?>, ConcurrentMap<String, Field>> getPlugins() {
        return PENDINGS;
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

    /**
     * Multi thread to scan EndPoint
     *
     * @param clazzes
     */
    private static void initEndPoints(final Set<Class<?>> clazzes) {
        final Set<Class<?>> endpoints =
                clazzes.stream()
                        .filter((item) -> item.isAnnotationPresent(EndPoint.class))
                        .collect(Collectors.toSet());
        if (ENDPOINTS.isEmpty()) {
            ENDPOINTS.addAll(endpoints);
            LOGGER.info(Info.SCANED_ENDPOINT, endpoints.size());

            final CountDownLatch counter = new CountDownLatch(ENDPOINTS.size());
            final List<EndPointThread> threadReference = new ArrayList<>();
            /** 2.1.Build Api metadata **/
            for (final Class<?> endpoint : ENDPOINTS) {
                final EndPointThread thread =
                        new EndPointThread(endpoint, counter);
                threadReference.add(thread);
                thread.start();
            }
            HTry.execJvm(() -> {
                counter.await();
                for (final EndPointThread item : threadReference) {
                    EVENTS.addAll(item.getEvents());
                }
                return null;
            }, LOGGER);
        }
    }

    /**
     * Multi thread to scan Queue
     *
     * @param clazzes
     */
    private static void initQueues(final Set<Class<?>> clazzes) {
        final Set<Class<?>> queues =
                clazzes.stream()
                        .filter(item -> item.isAnnotationPresent(Queue.class))
                        .collect(Collectors.toSet());
        if (RECEIPTS.isEmpty()) {
            LOGGER.info(Info.SCANED_QUEUE, queues.size());
            final CountDownLatch counter = new CountDownLatch(queues.size());
            final List<QueueThread> threadReference = new ArrayList<>();
            /** 3.1. Build Metadata **/
            // final Extractor<Set<Receipt>> extractor = Instance.singleton(ReceiptExtractor.class);
            for (final Class<?> queue : queues) {
                final QueueThread thread =
                        new QueueThread(queue, counter);
                threadReference.add(thread);
                thread.start();
            }
            HTry.execJvm(() -> {
                counter.await();
                for (final QueueThread item : threadReference) {
                    RECEIPTS.addAll(item.getReceipts());
                }
                return null;
            }, LOGGER);
        }
    }

    /**
     * Multi thread to scan Queue
     *
     * @param clazzes
     */
    private static void initPlugin(final Set<Class<?>> clazzes) {
        if (PENDINGS.isEmpty()) {
            
        }
    }

    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);
        /** 2.Preparing for ENDPOINTS **/
        initEndPoints(clazzes);
        /** 3.Set to QUEUES **/
        initQueues(clazzes);
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
                clazzes.stream()
                        .filter((item) -> item.isAnnotationPresent(Worker.class))
                        .collect(Collectors.toSet());
        if (WORKERS.isEmpty()) {
            WORKERS.addAll(workers);
        }
        /** 7.Scan all classes which could be inject **/

    }
}
