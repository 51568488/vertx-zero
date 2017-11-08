package io.vertx.up;

import com.vie.hoc.HTry;
import com.vie.log.Annal;

/**
 * Vertx Application start information
 */
public class VertxApplication {

    private static final Annal LOGGER = Annal.get(VertxApplication.class);

    public static void run(final Class<?> clazz) {
        // 1. Scan clazz for runtime
        HTry.execUp(() -> {
            
        }, LOGGER);
    }
}
