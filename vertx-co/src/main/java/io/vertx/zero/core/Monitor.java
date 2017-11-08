package io.vertx.zero.core;

import io.vertx.core.json.JsonObject;
import com.vie.em.Etat;

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
