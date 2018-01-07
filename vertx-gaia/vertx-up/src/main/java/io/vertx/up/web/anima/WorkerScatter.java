package io.vertx.up.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.tp.etcd.center.EtcdData;
import io.vertx.up.annotations.Worker;
import io.vertx.up.eon.em.MessageModel;
import io.vertx.up.func.Fn;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ZeroHttpWorker;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.WorkerExtractor;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Worker scatter to deploy workers
 */
public class WorkerScatter implements Scatter<Vertx> {

    @Override
    public void connect(final Vertx vertx) {
        /** 1.Find Workers for deploy **/
        final Set<Class<?>> sources = ZeroAnno.getWorkers();
        /** 2.Default Workers **/
        if (sources.isEmpty()) {
            sources.add(ZeroHttpWorker.class);
        }
        // Filter and extract by message model, this scatter only support
        // MessageModel equal REQUEST_RESPONSE
        final Set<Class<?>> workers = getTargets(sources);
        final Extractor<DeploymentOptions> extractor =
                Instance.instance(WorkerExtractor.class);
        final ConcurrentMap<Class<?>, DeploymentOptions> options =
                new ConcurrentHashMap<>();
        for (final Class<?> worker : workers) {
            // 2.1 Worker deployment options
            final DeploymentOptions option = extractor.extract(worker);
            options.put(worker, option);
            // 2.2 Worker deployment
            Verticles.deploy(vertx, worker, option, getLogger());
        }
        // Runtime hooker
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                Fn.itSet(workers, (clazz, index) -> {
                    // 4. Undeploy Agent.
                    final DeploymentOptions opt = options.get(clazz);
                    Verticles.undeploy(vertx, clazz, opt, getLogger());
                })));
    }

    private Annal getLogger() {
        return Annal.get(getClass());
    }

    private Set<Class<?>> getTargets(final Set<Class<?>> sources) {
        final Set<Class<?>> workers = new HashSet<>();
        for (final Class<?> source : sources) {
            final MessageModel model =
                    Instance.invoke(source.getAnnotation(Worker.class), "value");
            if (getModel().contains(model)) {
                workers.add(source);
            }
        }
        return workers;
    }

    protected Set<MessageModel> getModel() {
        // Enabled Micro model
        final Set<MessageModel> models = new HashSet<MessageModel>() {
            {
                add(MessageModel.REQUEST_RESPONSE);
            }
        };
        if (EtcdData.enabled()) {
            models.add(MessageModel.REQUEST_MICRO_WORKER);
        }
        return models;
    }

}
