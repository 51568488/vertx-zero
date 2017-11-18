package io.vertx.up.web;

import io.vertx.up.atom.Event;
import io.vertx.up.atom.Receipt;
import io.vertx.up.eon.em.ServerType;
import io.vertx.up.web.origin.*;
import io.vertx.zero.func.HBool;
import io.vertx.zero.log.Annal;
import io.vertx.zero.tool.Runner;
import io.vertx.zero.tool.mirror.Instance;
import io.vertx.zero.tool.mirror.Pack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Transfer Class<?> set to difference mapping.
 */
public class ZeroAnno {

    private static final Annal LOGGER = Annal.get(ZeroAnno.class);

    private final static Set<Class<?>>
            ENDPOINTS = new HashSet<>();
    private final static ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>
            PLUGINS = new ConcurrentHashMap<>();
    private final static Set<Receipt>
            RECEIPTS = new HashSet<>();
    private final static Set<Event>
            EVENTS = new HashSet<>();
    private final static ConcurrentMap<ServerType, List<Class<?>>>
            AGENTS = new ConcurrentHashMap<>();
    private final static Set<Class<?>>
            WORKERS = new HashSet<>();

    /**
     * Get all plugins
     *
     * @return
     */
    public static ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>
    getPlugins() {
        return PLUGINS;
    }

    /**
     * Get all agents.
     *
     * @return
     */
    public static ConcurrentMap<ServerType, List<Class<?>>>
    getAgents() {
        return AGENTS;
    }

    /**
     * Get all workers
     *
     * @return
     */
    public static Set<Class<?>>
    getWorkers() {
        return WORKERS;
    }

    /**
     * Get all receipts
     *
     * @return
     */
    public static Set<Receipt>
    getReceipts() {
        return RECEIPTS;
    }

    /**
     * Get all endpoints
     *
     * @return
     */
    public static Set<Class<?>>
    getEndpoints() {
        return ENDPOINTS;
    }

    /**
     * Get all envents
     *
     * @return
     */
    public static Set<Event>
    getEvents() {
        return EVENTS;
    }


    static {
        /** 1.Scan the packages **/
        final Set<Class<?>> clazzes = Pack.getClasses(null);

        Runner.run(() -> {
            /** EndPoint **/
            final Inquirer<Set<Class<?>>> inquirer =
                    Instance.singleton(EndPointInquirer.class);
            ENDPOINTS.addAll(inquirer.scan(clazzes));

            /** EndPoint -> Event **/
            HBool.exec(!ENDPOINTS.isEmpty(),
                    LOGGER,
                    () -> {
                        final Inquirer<Set<Event>> event =
                                Instance.singleton(EventInquirer.class);
                        EVENTS.addAll(event.scan(ENDPOINTS));
                    }, null);
        }, "zero-endpoint");

        Runner.run(() -> {
            /** Queue **/
            final Inquirer<Set<Class<?>>> inquirer =
                    Instance.singleton(QueueInquirer.class);
            final Set<Class<?>> queues = inquirer.scan(clazzes);

            /** Queue -> Receipt **/
            HBool.exec(!queues.isEmpty(),
                    LOGGER,
                    () -> {
                        final Inquirer<Set<Receipt>> receipt =
                                Instance.singleton(ReceiptInquirer.class);
                        RECEIPTS.addAll(receipt.scan(queues));
                    }, null);
        }, "zero-queue");

        /** Agent **/
        Runner.run(() -> {
            final Inquirer<ConcurrentMap<ServerType, List<Class<?>>>> inquirer =
                    Instance.singleton(AgentInquirer.class);
            AGENTS.putAll(inquirer.scan(clazzes));
        }, "zero-agent");

        /** Worker **/
        Runner.run(() -> {
            final Inquirer<Set<Class<?>>> inquirer =
                    Instance.singleton(WorkerInquirer.class);
            WORKERS.addAll(inquirer.scan(clazzes));
        }, "zero-worker");

        /** Injections **/
        Runner.run(() -> {
            final Inquirer<ConcurrentMap<Class<?>, ConcurrentMap<String, Class<?>>>> inquirer =
                    Instance.singleton(AffluxInquirer.class);
            PLUGINS.putAll(inquirer.scan(clazzes));
        }, "zero-afflux");
    }
}
