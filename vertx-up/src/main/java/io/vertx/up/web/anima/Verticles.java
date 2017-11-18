package io.vertx.up.web.anima;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.up.eon.Info;
import io.vertx.zero.log.Annal;

/**
 * Shared verticle method
 */
class Verticles {

    static void deploy(final Vertx vertx,
                       final Class<?> clazz,
                       final DeploymentOptions option,
                       final Annal logger) {
        // Verticle deployment
        final String name = clazz.getName();
        final String flag = option.isWorker() ? "Worker" : "Agent";
        vertx.deployVerticle(name, option, (result) -> {
            // Success or Failed.
            if (result.succeeded()) {
                logger.info(Info.VTC_END,
                        name, option.getInstances(), result.result(),
                        flag);
            } else {
                logger.info(Info.VTC_FAIL,
                        name, option.getInstances(), result.result(),
                        null == result.cause() ? null : result.cause().getMessage(), flag);
            }
        });
    }
}
