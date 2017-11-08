package io.vertx.up;

import com.vie.em.Etat;
import io.vertx.core.json.JsonObject;

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
