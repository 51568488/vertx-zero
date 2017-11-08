package io.vertx.zero.ke;

import io.vertx.core.json.JsonObject;
import io.vertx.zero.em.Etat;

public interface Monitor {
    /**
     * Health Check
     *
     * @return
     */
    JsonObject checkHealth();

    /**
     * Launcher Status
     *
     * @return
     */
    Etat getStatus();
}
