package io.vertx.up.micro;

import io.reactivex.Observable;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.up.annotations.Worker;
import io.vertx.up.concurrent.Runner;
import io.vertx.up.eon.em.MessageModel;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.discovery.EndPointOrgin;
import io.vertx.up.micro.discovery.Orgin;
import io.vertx.up.tool.mirror.Instance;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Backend for service discovery
 */
@Worker(value = MessageModel.DISCOVERY_PUBLISH, instances = 1)
public class ZeroApiWorker extends AbstractVerticle {

    private static final Annal LOGGER = Annal.get(ZeroApiWorker.class);

    private static final Orgin ORIGIN = Instance.singleton(EndPointOrgin.class);

    private static final ConcurrentMap<String, Record> REGISTRITIONS
            = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, String> ID_MAP
            = new ConcurrentHashMap<>();

    private static final AtomicBoolean initialized =
            new AtomicBoolean(false);

    @Override
    public void start() {
        final ServiceDiscovery discovery = ServiceDiscovery.create(this.vertx);
        // AtomicBoolean checking
        if (!initialized.getAndSet(true)) {
            // initialized once.
            initializeServices(discovery);
        }
        // Scan the services every 5s
        this.vertx.setPeriodic(5000, id -> {
            // Read the latest services
            final ConcurrentMap<String, Record> services = ORIGIN.getRegistryData();

            // Read the down services
            final ConcurrentMap<Flag, Set<String>> resultMap = calculateServices(services);

            // Do the modification with thread.
            Runner.run(
                    () -> deleteService(discovery, resultMap.get(Flag.DELETE)),
                    "discovery-deleted");
            Runner.run(
                    () -> updateService(discovery, resultMap.get(Flag.UPDATE)),
                    "discovery-updated");
            Runner.run(
                    () -> addService(discovery, resultMap.get(Flag.NEW), services),
                    "discovery-added");
        });
    }

    private void deleteService(final ServiceDiscovery discovery,
                               final Set<String> ids) {
        // Delete service from current zero system.
        Observable.fromIterable(ids)
                .subscribe(id -> {
                    final String item = ID_MAP.get(id);
                    discovery.unpublish(item, result -> {
                        if (result.succeeded()) {
                            // Delete successfully
                            final Record record = REGISTRITIONS.get(id);
                            LOGGER.info(Info.REG_SUCCESS, id,
                                    item, record.getName(),
                                    record.getLocation(), record.getType(), record.getStatus(),
                                    "Delete");
                            // Sync deleted
                            REGISTRITIONS.remove(id);
                            ID_MAP.remove(id);
                        } else {
                            LOGGER.info(Info.REG_FAILURE, result.cause().getMessage(), "Delete");
                        }
                    });
                });
    }

    private void updateService(final ServiceDiscovery discovery,
                               final Set<String> ids) {
        // Update service into current zero system.
        Observable.fromIterable(ids)
                .map(REGISTRITIONS::get)
                .subscribe(item -> discovery.update(item, result -> {
                    if (result.succeeded()) {
                        final Record record = result.result();
                        // Update successfully
                        successFinished(record, "Update");
                    } else {
                        LOGGER.info(Info.REG_FAILURE, result.cause().getMessage(), "Update");
                    }
                }));
    }

    private void addService(final ServiceDiscovery discovery,
                            final Set<String> ids,
                            final ConcurrentMap<String, Record> services) {
        // Add service into current zero system.
        Observable.fromIterable(ids)
                .map(services::get)
                .subscribe(item -> discovery.publish(item, result -> {
                    if (result.succeeded()) {
                        final Record record = result.result();
                        // Add successfully
                        successFinished(record, "Add");
                    } else {
                        LOGGER.info(Info.REG_FAILURE, result.cause().getMessage(), "Add");
                    }
                }));
    }


    private void initializeServices(final ServiceDiscovery discovery) {
        // Read the services
        final Set<Record> services = new HashSet<>(ORIGIN.getRegistryData().values());
        Observable.fromIterable(services)
                .subscribe(item -> discovery.publish(item, result -> {
                    if (result.succeeded()) {
                        final Record record = result.result();
                        // Initialized successfully
                        successFinished(record, "Init");
                    } else {
                        LOGGER.info(Info.REG_FAILURE, result.cause().getMessage(), "Init");
                    }
                }));
    }

    private ConcurrentMap<Flag, Set<String>> calculateServices(
            final ConcurrentMap<String, Record> services) {
        // Read new services.
        final Set<String> populated = new HashSet<>();
        Observable.fromIterable(services.keySet())
                .subscribe(populated::add);

        // Existed = Yes, Populated = No
        final Set<String> deleted = new HashSet<>(REGISTRITIONS.keySet());
        deleted.removeAll(populated);

        // Existed = Yes, Populated = Yes
        final Set<String> updated = new HashSet<>(REGISTRITIONS.keySet());
        updated.retainAll(populated);

        // Existed = No, Populated = Yes
        final Set<String> added = new HashSet<>(populated);
        added.removeAll(REGISTRITIONS.keySet());

        // Mapping data
        final ConcurrentMap<Flag, Set<String>> result = new ConcurrentHashMap<>();
        result.put(Flag.DELETE, deleted);
        result.put(Flag.NEW, added);
        result.put(Flag.UPDATE, updated);
        return result;
    }

    private void successFinished(final Record record, final String action) {
        // Build key
        final String key = getID(record);
        final String id = record.getRegistration();
        LOGGER.info(Info.REG_SUCCESS, key,
                id, record.getName(),
                record.getLocation(), record.getType(), record.getStatus(),
                action);
        // Fill container
        REGISTRITIONS.put(key, record);
        ID_MAP.put(key, id);
    }

    private String getID(final Record record) {
        final JsonObject metadata = record.getMetadata();
        return metadata.getString(Orgin.ID);
    }

    private enum Flag {
        NEW,
        UPDATE,
        DELETE
    }
}
