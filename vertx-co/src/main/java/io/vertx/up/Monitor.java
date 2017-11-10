package io.vertx.up;

import io.vertx.core.json.JsonObject;
import org.vie.cv.em.Etat;

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
