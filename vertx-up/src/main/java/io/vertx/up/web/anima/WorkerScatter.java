package io.vertx.up.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.up.log.Annal;
import io.vertx.up.micro.ZeroHttpWorker;
import io.vertx.up.rs.Extractor;
import io.vertx.up.rs.config.WorkerExtractor;
import io.vertx.up.tool.mirror.Instance;
import io.vertx.up.web.ZeroAnno;

import java.util.Set;

/**
 * Worker scatter to deploy workers
 */
public class WorkerScatter implements Scatter {

    private static final Annal LOGGER = Annal.get(WorkerScatter.class);

    @Override
    public void connect(final Vertx vertx) {
        /** 1.Find Workers for deploy **/
        final Set<Class<?>> workers = ZeroAnno.getWorkers();
        final Extractor<DeploymentOptions> extractor =
                Instance.instance(WorkerExtractor.class);
        /** 2.Default Workers **/
        if (workers.isEmpty()) {
            workers.add(ZeroHttpWorker.class);
        }
        for (final Class<?> worker : workers) {
            // 2.1 Worker deployment options
            final DeploymentOptions option = extractor.extract(worker);
            // 2.2 Worker deployment
            Verticles.deploy(vertx, worker, option, LOGGER);
        }
    }
}
