package io.vertx.up.web.anima;

import io.vertx.up.eon.em.MessageModel;

/**
 * Service discovery usage.
 */
public class DetectScatter extends WorkerScatter {

    @Override
    protected MessageModel getModel() {
        return MessageModel.DISCOVERY_PUBLISH;
    }
}
